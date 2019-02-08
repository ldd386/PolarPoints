package ch.example.polarpoints.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ch.example.polarpoints.PolarpointsApplication
import ch.example.polarpoints.R
import ch.example.polarpoints.api.PolarApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private lateinit var viewBindings: MainFragmentViewBindings

    lateinit var polarApi : PolarApi
    lateinit var activityInDayListAdapter : ActivityInDayListAdapter

    val activityInADayList = ArrayList<ActivityInDayListItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBindings = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return viewBindings.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView = viewBindings.pointsActivities
        activityInDayListAdapter = ActivityInDayListAdapter(
                context!!,
                R.id.points_activities,
                activityInADayList
        )
        listView.adapter = activityInDayListAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            Log.i("MainFragment", "list item clicked, id: " + id)
        }
        Log.i("MainFragment", "onCreate called")
        // getting polarapi object from application
        if((activity!!.application as PolarpointsApplication).polarApi  == null){
            (activity!!.application as PolarpointsApplication).polarApi = PolarApi()
        }
        polarApi = (activity!!.application as PolarpointsApplication).polarApi!!
        handleIntent()
    }

    private fun handleIntent() {
        Log.i("MainFragment", "handling intent")
        if(activity!!.intent != null && activity!!.intent.data != null){
            val requestedUrl = activity!!.intent.data.toString()
            val startIndex = requestedUrl.indexOf("code=")
            val authCode = requestedUrl.substring(startIndex+5)
            Log.i("MainFragment", String.format("auth code for Oauth2 is: %s", authCode))
            continueAuth(authCode)
        } else {
            Log.i("MainFragment", "starting auth")
            startAuth()
        }
    }

    private fun startAuth() {
        GlobalScope.launch {
            val authUrl = polarApi.authUrl
            openTabWithUrl(authUrl)
        }
    }

    private fun openTabWithUrl(authUrl: String) {
        val builder = CustomTabsIntent.Builder()
        val tabIntent = builder.build()
        tabIntent.launchUrl(context, Uri.parse(authUrl))
    }

    private fun continueAuth(authCode: String) {
        GlobalScope.launch {
            polarApi.doAuth(authCode)
            loadTodayUserData()
        }
    }

    private fun loadTodayUserData() {
        GlobalScope.launch {
            // getting Profile
            val profile = polarApi.loadProfile()
//            val exerciseTransaction = polarApi.createExerciseTransaction()
//            val exercises =  polarApi.listExercisesInTransaction(exerciseTransaction!!.transactionId)
//            val exerciseId = exercises!!.exercisesId!!.last()
//            polarApi.getHeartRateZonesForExercise(exerciseTransaction!!.transactionId, exerciseId)
//            polarApi.commitExerciseTransaction(exerciseTransaction!!.transactionId)
            val activityTransaction = polarApi.createActivityTransaction()
            val activities = polarApi.listActivitiesInTransaction(activityTransaction!!.transactionId)
            val activitySummary = polarApi.getActivitySummary(activityTransaction!!.transactionId, activities!!.activitiesId.last())
            polarApi.commitActivityTransaction(activityTransaction!!.transactionId)
        }
    }
}
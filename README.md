# Fragments  

<img src="https://developer.android.com/images/fundamentals/fragments.png"   />

A fragment is a reusable class implementing a portion of an activity. A Fragment typically defines a part of a user interface. Fragments must be embedded in activities; they cannot run independently of activities.   


**Importance of Fragments**  

There are many use cases for fragments but the most common use cases include:  

- Reusing View and Logic Components: Fragments enable re-use of parts of your screen including views and event logic over and over in different ways across many disparate activities. For example, using the same list across different data sources within an app.

- Tablet Support: Often within apps, the tablet version of an activity has a substantially different layout from the phone version which is different from the TV version. Fragments enable device-specific activities to reuse shared elements while also having differences.  

- Screen Orientation: Often within apps, the portrait version of an activity has a substantially different layout from the landscape version. Fragments enable both orientations to reuse shared elements while also having differences.  

**Fragment Life cycle**  
<img src="https://developer.android.com/images/fragment_lifecycle.png"   />

| Method  | Description |
| ------------- | ------------- |
|onAttach()| is called when a fragment is connected to an activity |
|onCreate()| is called to do initial creation of the fragment |
|onCreateView()| is called by Android once the Fragment should inflate a view |
|onViewCreated()| is called after `onCreateView()` and ensures that the fragment's root view is non-null. Any view setup should happen here. E.g., view lookups, attaching listeners.|
|onActivityCreated()| is called when host activity has completed its onCreate() method |
|onStart()| is called once the fragment is ready to be displayed on screen |
|onResume()| Allocate expensive resources such as registering for `location`, `sensor updates`, etc. |
|onPause()| Release expensive resources. Commit any changes |
|onDestroyView()| is called when fragment's view is being destroyed, but the fragment is still kept around |
|onDestroy()| is called when fragment is no longer in use |
|onDetach()| is called when fragment is no longer connected to the activity |

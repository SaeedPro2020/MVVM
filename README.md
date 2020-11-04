# MVVM
This a demo app which descripe MVVM pattern in mobile apps
In this app, we implemented MVVM (Model-View-ViewModel) pattern to send and retrieve data from WEB service.
Here, there is number of libraries that we have in this App.
"retrofit" and "moshi" to get data from database and parse it. (Also, we can use "gson" and dependency is added)
"coroutines" to do networking in background,
"glide" to get image data from server,
"Room" to create a local database,
a shared "viewModel" to persist data, 
"databinding" for passing data to xml file.
 
In View(UI), we only present the data which we will get from ViewModel, while Model provide that data and then pass it to the SharedViewModel.

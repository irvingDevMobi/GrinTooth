# GrinTooth

Android proyect to detect bluetooth devices and also save devices to a web services 

I apply MVVM architecture to develop the proyect

View <---> ViewModel <---> datamodel 

View: 
  - Layer dependent of platform, responsable to render Ui and pass Ui event to ViewModel
  - I use Activity as Views

ViewModel
  - Layer free of Android Context, responsable to notify the UI when to draw something and process the Ui inputs from the view
  - I use Android View Model for this clasess
....... (in Progress..)

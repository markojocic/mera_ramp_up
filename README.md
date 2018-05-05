# mera_ramp_up
# starting project 

Android - Developer Ramp up

Here is a description of the task to ramp up your programming skills and knowledge.

1. Add the functionality to get a list of "tetris"-repositories from the REST API call:   https://api.github.com/search/repositories?q=tetris

2.  The call returns JSON entries for each repository. Further documentation can be found at: https://developer.github.com/v3/search/#search-repositories  

3. Parse the result and display the returned items in a grid layout, using RecyclerView.  

4. Each item should show the name of the repository, the login – name of it's owner and  the size of the repository.  

5. Use a different background color for items that have "has_wiki" set to 'true'   

6. Implement pagination: Get 10 entries with every REST-call and extend the list by 10 more whenever the list is scrolled to the end. Read the online documentation to find out how. (pay heed to the rate limit: https://developer.github.com/v3/search/#rate- limit)  

7. Add a text field where a user can enter an arbitrary search string instead of "tetris"

8. Add a contacts screen that will get all the contacts from the phone and display them in a RecyclerView (create a model for contact which contains name, surname, picture if available and a phone number). Tapping the cells should bring up the native phone dialer with that contact’s phone number. The needs to show permission dialog for accessing the contacts,

which will take the user to the previous screen if it is dismissed, and show again when you try to open the contacts screen until it is accepted.

9. Show a “no internet connection” which will show up when the Internet is disconnected and will go away if the Internet is reconnected.

10. Set a global timer that will create a local notification that will pop up on every 5 minutes.

11.  Notes:  

* Use the Android native libraries.  

* The goal is a functional app.  

* Usage of git.

* For points 1. and 2. consider using Retrofit library.

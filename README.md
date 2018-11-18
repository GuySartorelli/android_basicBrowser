# android_basicBrowser
A simple browser for Android to learn basic android app creation

##functionalities:
Text typed in the location bar will be automatically searched via google if A: spaces exist in the string or B: no dot (.) exists in the string
i.e. "this is a search" and "search" will both be searched.
Anything else will be interpreted as a url
e.g. "google.com" will be interpreted to be "https://www.google.com/"

Whenever a new page begins to load (even via link clicked in webview) the location bar will be updated with the appropriate URL.

Pressing the phone's physical back button will go to the previous loaded page if there is one. If there isn't, you are at the home page and your app will be closed. 
Because I didn't get around to adding a home page, the first page you navigate to is considered "home".

To go forward, tap the overflow menu button (three dots) and tap forward.

To refresh the page, either drag the webview downwards (when at the top of the page) or tap the overflow menu button and then refresh.

To visit previously visited pages, tap the overflow menu button and then history.
Tapping back from here will return you to the webpage you were already on.
Tapping on a history item here will navigate to that history item.
Tapping "clear history" will remove all history items.
These should (hopefully) be stored persistently between sessions.
Note I originally had favicons visible here but they caused crashing issues so they have been hesitantly removed.
Although this may be more difficult to test, history items are stored by day; each day for which there are history items will have a date heading, under which all history items for that day are displayed.

Rotating the screen does not require the app to close and reload, making a more seemless transition.

Current page-load progress is displayed via a progress bar underneath the location bar. This is hidden after the page has finished loading.

"secure" websites (i.e. urls beginning with https://) have a closed padlock symbol in the location bar.
"insecure websites" (http://) have an open padlock symbol in the location bar.
Before navigating to a website for the first time and afterwards when the location bar has focus, a "search" icon is displayed instead.

When the location bar has focus, a backspace/delete icon is displayed on the right hand side of the location bar. Tapping this will remove any text in the location bar.

When the location bar loses focus its text will revert to the currently viewed URL.

If you press the home button to temporarily hide the app, opening it again will open right back to where you were.

Cookies and javascript are enabled. Cookies are not, however, stored persistently between sessions.

I think that's everything. I didn't get around to a TON of things I wanted to do, but that's the nature of these things.
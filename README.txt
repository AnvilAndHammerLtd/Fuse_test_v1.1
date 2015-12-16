Fuse Mobile Coding Test v1.1

Objective: Validate a company exist within Fuse platform, you may use any
open source library/framework such as AFNetworking, Alamofire, Retrofit,
EventBus, ReactiveCocoa etc… Please develop for a production environment,
Git commit regularly, add comment when necessary, unit test etc…

1. Create a new public git repo on Github

2. Create new project using your preferred development platform (i.e iOS dev use
XCode and Android use Android Studio):
iOS: Use Cocoapods setup (https://cocoapods.org/)
Android: Use Gradle setup

3. Add a Textfield which is width of the device for both portrait and landscape and set
the keyboard type to Done or Go

4. On keyboard return make a network request to validate the company name using
the following API:
GET https://[COMPANY NAME].fusion-universal.com/api/v1/company.json
Note: please replace [COMPANY NAME] with the text from the textfield

4.1 Validate length of text is more then 1 and remove whitespace from text before
sending a request

5. If the response is successful (status code: 200): create a model of the response
values

5.1 Make the textfield green for status code 200 otherwise make it red
Note: beware of threading (background and main thread) and retain cycle

5.2 Replace the textfield text with the response key “name” value from your model
Company name request: fusion

Request example: https://fusion.fusion-universal.com/api/v1/company.json

Response example:
{
  "name": "Fuse",
  "logo": "http://fusion.fusion-universal.com…”,
  "custom_color": "#ea2184",
  "password_changing": {
    "enabled": false,
    "secure_field": null
  }
}

6. Download the logo image using the model key “logo”

6.1 Present the downloaded image below the textfield with size: 100x100

7. On textfield retry reset the textfield colour to white and remove the image from the
screen if it present

8. Commit code to Github once complete

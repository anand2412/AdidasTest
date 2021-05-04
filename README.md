# AdidasTest

This application has two screens as below:

1) Products list with search your favorite product
2) Product details with its reviews and user can add review

Note: Price is not updated because in api we have empty currency. Tried to use put api to add currency but same product has not been updated.

Below points need to be checked

1) App Resilience - 
Retrofit has been used to connect to api and fetch the data. So in case of no internet connection, we receive error and same shown to the user with an image and text in place of product list

2) App Stability -
To analyze the crashes we can use crashlytics, google analytics or flurry analytics. So developer team can get the report and fix. Once it will get fix, will update same to playstore. We can prvide force update using Firebase

3) Testing -
I have used Roboelctric to run unit test cases. Currently I have added test cases only for apis. Later we can add test cases for activity and views as well.

4) App Architecture
Arhtecture of any project is very much needed to provide flexibility in code and make code more understandable. Currently we have used MVVM 

5) UI/UX -
This is very important when we need to deal with multiple devices and screens sizes. Also correct UI should be used to make application more interractive and user firendly. Light weight images should be used to make application performance better.

6) Building app -
continuous integration, is a development practice in which each member of a team frequently merges their codes into the main repository branch. Each integration triggers an automated build and test workflow, allowing the team to detect and fix problems as early as possible.

![1](https://user-images.githubusercontent.com/6871946/117029722-91b24100-ad1c-11eb-8084-a6f1d0a08ad6.png)
![2](https://user-images.githubusercontent.com/6871946/117029747-9676f500-ad1c-11eb-8e8e-c5358bdad325.png)


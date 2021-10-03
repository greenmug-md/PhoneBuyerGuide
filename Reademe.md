
# Phone Buyer Guide


## Functional Requirements
# Mobile Phone Buyer Guide

# Functional Requirement

## List screen
• The page is separated into sections by 2 tabs (list and favorites).
• Display list of mobile phones obtained from the API in the list section.
• Each item of the list should contain thumbnail image, title, description (tail truncated at two lines), price,
rating and a favourite icon.
• When a user taps on the favorite icon, it should add the mobile phone to the favorite section.
• In the favorite section, a user should be able to swipe to delete to remove a mobile phone from favorite.
• An options button on the top right of the list screen should offer sorting options. Tapping on the sorting
button, the following list of options should appear:
o Sort by: 1. Price (low to high) 2. Price (high to low) 3.Rating(5to1)
• Sorting option that is selected should apply to both full list and favorite list sections.
• Tapping on a mobile phone entry should bring the user to the details screen.

## Details screen
• .Obtain a list of images related to the mobile phone from the API.
• The images should be horizontally scrollable and should consume 35% of the screen height of the
• device.
• The price and rating should appear on top of the image as the overlay.
• The full description should appear below the images.
 
## Technology Stack

- Backend:  
  - Mobile Api https://scb-test-mobile.herokuapp.com/api/mobiles/
  - Mobile Api https://scb-test-mobile.herokuapp.com/api/mobiles/{mobile_id}/images

  
- Frontend: **Android using Kotlin**
  -  Material UI as Theme and Styling library
  -  Android Architecture using Model View ViewModel (Android Lifecycle aware component).
  -  Room for Local Database
  -  Coroutines with RxJava for Network Calls
  -  Android Unit Testing

Mobile All Tab 
    
![alt text](https://user-images.githubusercontent.com/34758872/135751914-98b725a9-5c90-4d14-84d7-2bdda2901eaa.png)


Favourites Tab

![Screenshot_20211003-170235](https://user-images.githubusercontent.com/34758872/135751932-2ebd6432-b22f-4349-88e3-d59abdb6a335.png)

Sorting


![Screenshot_20211003-170240](https://user-images.githubusercontent.com/34758872/135751941-8f44a8a3-2f2c-4693-901d-9d4e8d93e89d.png)


Detail

![Screenshot_20211003-170252](https://user-images.githubusercontent.com/34758872/135751952-0d9bce36-483f-48b0-95eb-728681bddb77.png)


Swipe to Delete


![Screenshot_20211003-170316](https://user-images.githubusercontent.com/34758872/135751966-9a9bb8be-25bf-4ebe-afb5-648d05c82451.png)


 

## Unit Testing
<img width="623" alt="Screenshot 2021-10-03 at 5 01 19 PM" src="https://user-images.githubusercontent.com/34758872/135751732-a0a7905a-5f96-4cf1-8f6e-030020de3089.png">


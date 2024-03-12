# Journal
Multi-module Android app built with Jetpack Compose and the latest android libraries. [InProgress.....]

# Modularization Style: 
- Hybrid (By layer and By Feature)

# Preview 
<p float="left">
<img width="30%"  alt="Screenshot 2023-08-23 at 4 11 00 PM" src="https://github.com/mrabelwahed/NewsApp/blob/master/screenshots/1.png">
<img width="30%"  alt="Screenshot 2023-08-23 at 4 11 00 PM" src="https://github.com/mrabelwahed/NewsApp/blob/master/screenshots/2.png">
<img width="30%"  alt="Screenshot 2023-08-23 at 4 11 00 PM" src="https://github.com/mrabelwahed/NewsApp/blob/master/screenshots/3.png">
<img width="30%"  alt="Screenshot 2023-08-23 at 4 11 00 PM" src="https://github.com/mrabelwahed/NewsApp/blob/master/screenshots/4.png">
<img width="30%"  alt="Screenshot 2023-08-23 at 4 11 00 PM" src="https://github.com/mrabelwahed/NewsApp/blob/master/screenshots/5.png">
<img width="30%"  alt="Screenshot 2023-08-23 at 4 11 00 PM" src="https://github.com/mrabelwahed/NewsApp/blob/master/screenshots/6.png">
<img width="30%"  alt="Screenshot 2023-08-23 at 4 11 00 PM" src="https://github.com/mrabelwahed/NewsApp/blob/master/screenshots/7.png">
<img width="30%"  alt="Screenshot 2023-08-23 at 4 11 00 PM" src="https://github.com/mrabelwahed/NewsApp/blob/master/screenshots/8.png">
<img width="30%"  alt="Screenshot 2023-08-23 at 4 11 00 PM" src="https://github.com/mrabelwahed/NewsApp/blob/master/screenshots/9.png">
<img width="30%"  alt="Screenshot 2023-08-23 at 4 11 00 PM" src="https://github.com/mrabelwahed/NewsApp/blob/master/screenshots/10.png">
<img width="30%"  alt="Screenshot 2023-08-23 at 4 11 00 PM" src="https://github.com/mrabelwahed/NewsApp/blob/master/screenshots/11.png">
</p>

<br><br>

# Used Libs:
- Jetpack Compose
- Paging3
- Room
- DataStore
- Hilt
- Jetpack components
- Coil 
- Support Dark Theme
- Support Version Catalog

# Plan
- Add Unit testing using Junit and Mockk
- Add UI testing  
- Add Snapshot testing
- Add cool Animations
- Add Native Module for Keys
- Add a common gradle file instead of dependency redundancy
- Add CI/CD using GitHub actions

# Current Modules

- design system : holds fonts/colors/types/theme + resources +localisation (needs refactor)
- ui components: holds some reusable components
- Features
  - onboarding 
  - news  
  - bookmarks 
  - news search
  - news details
- Core
  - data  for repository and mappers 
  - domain  for use cases and business
  - database for local source
  - network for remote source
  - common for shared logic and utils

# InProgress ......
 - Implement UI Tests using Robot pattern and Mock Http Server

# Support Me
https://www.buymeacoffee.com/ramadan

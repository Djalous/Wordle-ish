# SWE 2710 111 Team B Wordle-esque Game
# Copyright 2024 SWE 2710 111 Team B ( Duaa "DJ" Aljalous, Lazar Jovanovic,Theresa Kettner, Jack Rosenbecker)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
## Known Bugs/Issues/Defects/Trouble

During Game Play
1. Error Message - when entering an invalid guess in last row which prompts the error message label
to display and then the user quickly enters a valid guess that is NOT the target word within the same duration
that the error message label is still visible, the target word that then shows after losing a game will be integrated
into the delay of the previous error message label and will delete after a short amount of time (it should stay there indefinitely)

Creating an Account

1. If you don't select user type when creating a new user, the program will crash (stack trace will show)
Doesn't save user credentials...YET

### Bugs discovered from Bug Bounty 4/25/2024
1. Even after you win the game, you can delete the row of letters you guessed
2. When I go to make a new account (specifically one that is a middle schooler), I am not able to make it (the window doesn't move to the game; it's stuck at the create account screen).
   Reproduction Steps
   go to log in, pick a random user name (not "admin"), put in a random password, pick middle schooler as the User Type and then press enter.
3. Once I have entered six guesses and the game provides the correct reference word, I am still able to delete characters from the last row on the gameboard when pressing the 'Delete' button in the GUI.
4. On Screen keyboard "W" key shows 3 dots.
5. Allows typing after game is over and can change letters on last guess row.

### Prerequisites

Want to contribute to this project? \
You will need the following: \
Java SDK 17 and JavaFX 19 is required to run this project.

### Running the Application
Double click on the s24-riley-b.jar provided to play the Wordle-esque game.

### Description
This is a Wordle-esque app implemented using Java and JavaFX. Our application has the following features: 
- [x] Guessing a 5-letter word from a word bank
- [x] Ability to enter up to 6 guesses
- [x] Logic to check current guess against valid guess word bank
- [x] Store current guess
- [x] View correct letters in their places or almost correct places (green right letter, in right place, yellow right letter, in wrong place)
- [x] Appropriate error messages depending on what invalidity is present in user's guess
- [x] Toggle between Admin/User View
- [x] Admin Dashboard 
- [x] View your current number of guesses (In Development)
### Future Features
- [ ] Android Version (In Development)
- [ ] View your average guesses over all your games (In Development)
- [ ] View your past most commonly guessed words and letters (college users only)
- [ ] View a hint (middle school users only)
- [ ] View user's guessed words as an Admin
- [ ] View the total counts of letters guessed by all users as an Admin
- [ ] View recommended words to add as Admin
- [ ] Change the vocabulary file as Admin
- [ ] Change the lengths of words that users have to guess as Admin
- [ ] Input vocab files of words of different lengths
- [ ] Test Wordle words using AI
- [ ] Simulate Wordle games using text files


## Usage

To start the game, double-click on the s24-riley-b.jar file provided in the repository. 
This will open the game in a new window.
### Playing the Game
Simply use your keyboard to type in a 5-letter word and press enter.\
You have six chances to guess the word. If you guess the word correctly, you win!

## Credits
- Lazar Jovanovic (GitLab Tag: @lazar_j)
- Duaa "DJ" Aljalous (GitLab Tag: @aljalousd)
- Jack Rosenbecker (GitLab Tag: @CorneliusCornbread)
- Theresa Kettner ([Website](https://lazermaker.wixsite.com/theresakettner/))
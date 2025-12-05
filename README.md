# Address Book Project

This project is a simple desktop Address Book application built in Java using Swing. It allows users to add, update, and delete contacts, with each contact containing a name, phone number, and email address. All changes are saved automatically to a text file so that the contact list persists between sessions.

## Features

- Add new contacts  
- Edit existing contacts  
- Delete contacts  
- Automatically save data after every change  
- Load existing contacts when the program starts  
- Simple, easy-to-use Swing interface  

## Project Structure

The application is organized into four classes:

### Main
Starts the program and opens the GUI

### AddressBookGUI
Handles everything related to the user interface. It displays the list of contacts, manages the input fields, responds to button presses, and passes all data operations to the ContactManager

### ContactManager
Stores and manages all contacts. It maintains the in-memory list of contacts, handles adding, updating, and deleting entries, and manages reading from and writing to the save file

### Contact
Represents a single contact with a name, phone number, and email address

## File Persistence

All contacts are saved in a file named:

C:\Users\<YourUser>\Desktop\addressbook_data.txt

The file is updated automatically whenever a contact is created, edited, or removed

## Running the Program

From the project root:

./gradlew run

(Use "gradlew.bat run" on Windows.)

If running manually:

cd src/main/java/addressbook  
javac *.java  
java Main

## Author

Sean McGary  
Texas State University — CS 3354 (Object-Oriented Design)

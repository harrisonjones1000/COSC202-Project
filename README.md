# _Welcome to our **project Andie**, a non-destructive image editor._

# User Manual:
The main use of this program is to give you the ability to non-destructively edit an image with the option of just exporting it as a png file.
## In the Menubar you have a series of buttons which all perform their own features respectively these buttons include:

**NB**: When you hover over buttons a brief description of what the button does will pop-up.
### <br> 1. File:
 - will include button of the JMenuBar under File here.
###  <br>2. Edit:
### <br>3. View:
### <br>4. Filter:
 - Please include buttons and high-level description of what each button does here.
### <br>4. Colour:
-  Please include buttons and a high-level description of the Color buttons here.
### <br>5. Language:
### <br>6. Transformations:
 - Please include buttons and high-level description of what each button does here. 
### <br>7. Select:
 - Please provide a high-level description of what this feature does here.











## Contributions
Ryan- Sharpen Filter, Median Filter, Gaussoan Blur Fliter, Unit Test Implementation.

Harrison - Image Resize, Image Rotation, Image Flip

Owethu - Multilingual Support, Image Export

Emanuel - Exception Handling, Other Usability Error Avoidance/Prevention

Calan - Image Inversion, Colour Channel Cycling

## How code was tested
Unit tests were made for the Gaussian Blur Filter
Other features were tested by implementing them and visually comparing the operations to out estimation of the expected output.
Errors were identified after manually testing each feature with actions normal or abnormal, and rectified with code and re-testing.

## List of known bugs/issues
1) Median Filter - On even array size, the 'median' is not the mean of the 2 centre values but the value on the left (Very Minor)

2) Exit - Currently forces user to Save As if user presses exit button without saving changes, rather than saving the image normally (Minor)

3) Multilingual support - The program does not update the JMenuBar with updated preferences, but user has to restart the program to get the updated JMenuBar (minor).

4) Export image - Currently the program crashes when attempting to save in another directory other than the home directory.(Major)

5) Open Image - When opening another image while currently editing an image, does not save any changes made to the image. (Minor)


## Future Change Ideas
- Create a custom __JFileChooser__ so it can support multilingual support since currently there are default values for the labels in English **Look in**, **File Name:**, **Files of Type:**, **Open** button, **Cancel** button and all other labels to have multilingual support. 
- Make all menu buttons apart from 'File' and 'Language' either have an error message display when no image is in the ImagePanel, or make
the buttons unclickable until the user has selected an image to edit.
- Ask the user to save changes when exiting using the 'X' exit button as well as the 'File, Exit' button.
  

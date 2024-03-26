## Contributions
Ryan- Sharpen Filter, Median Filter, Gaussoan Blur Fliter, Unit Test Implementation.
Harrison - Image Resize, Image Rotation, Image Flip
Owethu - Multilingual Support, Image Export
Emanuel - Exception Handling, Other Error Avoidance/Prevention
Calan - Image Inversion, Colour Channel Cycling

## How code was tested
Unit tests (only for Gaussian Blur Filter)

## List of known bugs/issues
1) Median Filter - On even array size, the 'median' is not the mean of the 2 centre values but the value on the left (Very Minor)

2) Save/Save as - Currently causes the application to crash if attempting to save with no image in application(Major) -- FIXED

3) Open - Cannot open .ops files (ie, cannot open files that have been saved with save as, says incorrect file type) (Semi-major) -- FIXED

4) Exit - Currently forces user to Save As if user presses exit without saving changes, rather than saving the image normally (Minor)

5) Multilingual support - The program does not update the JMenuBar with updated preferences, but user has to restart the program to get the updated JMenuBar (Semi-major).

6) Export image - Currently the program crashes when attempting to save in another directory other than the home directory.

## User Guide
To use andie, after opening the application, click File, then open, then find the image file you want to edit. After which, you may zoom in and out under view, undo and redo under Edit, Filter to blur/sharpen the image, Colour to alter the colour of the image, Language to change the language of the application (must close and reopen the application first and only english and Māori are implimented), and transform to resize, flip, and rotate the image. After editing the image, you may save the image, save the image as a copy, or export the image.

## Future Change Ideas
- Add a window that pops up upon exiting the program if an image has been edited during that run, but hasn't been saved -- ADDED
- 
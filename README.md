## Contributions
Ryan- Sharpen Filter, Median Filter, Gaussoan Blur Fliter, Unit Test Implementation.
Harrison - Image Resize, Image Rotation, Image Flip
Owethu - Multilingual Support, Image Export
Emanuel - Exception Handling, Other Error Avoidance/Prevention
Calan - Image Inversion, Colour Channel Cycling

## How code was tested
Unit tests (only for Gaussian Blur Filter)

## List of known bugs/issues
Median Filter - On even array size, the 'median' is not the mean of the 2 centre values but the value on the left (Very Minor)
Save/Save as currently causes the application to crash (Major)
Cannot open .ops files (ie, cannot open files that have been saved with save as) (Semi-major)

## User Guide
To use andie, after opening the application, click File, then open, then find the image file you want to edit. After which, you may zoom in and out under view, undo and redo under Edit, Filter to blur/sharpen the image, Colour to alter the colour of the image, Language to change the language of the application (must close and reopen the application first and only english and Māori are implimented), and transform to resize, flip, and rotate the image. After editing the image, you may save the image, save the image as a copy, or export the image.
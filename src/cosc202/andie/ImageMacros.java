package cosc202.andie;

import java.util.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

//I, Calan, probably won't end up using this, but it is still a good ideas bucket for my implementation in EditableImage.

/**
 * <p>
 * A set of operations that can be applied to an image.
 * </p>
 * 
 * <p>
 * The ImageMacros represents a series of operations.
 * It is derived from {@link EditableImage}, the central data structure of the
 * Andie program.
 * EditableImage contains the image and operations, and the goal of ImageMacros
 * is to disentangle the operations from the image, so that it can be applied to
 * multiple images independently.
 * </p>
 * 
 * <p>
 * The operations are stored on a {@link Stack}, with a second {@link Stack}
 * being used to allow undone operations to be redone.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Calan McDermott, Steven Mills who created EditableImage which this is
 *         derived from.
 * @version 1.0
 */
public class ImageMacros {
        /** The sequence of operations currently stored in the Macros. */
        private Stack<ImageOperation> ops;
        /** A memory of 'undone' operations to support 'redo'. */
        private Stack<ImageOperation> redoOps;
        /** The file where the operation sequence is stored. */
        private String opsFilename;
        private boolean editable;

        public void startMacros(){
                if(editable){
                        System.out.println("Macros editing already active");
                        return;
                }
                editable = true;
                ops.clear();
                redoOps.clear();
        }
        /**
         * <p>
         * Add an {@link ImageOperation} to this macros.
         * </p>
         * 
         * @param op The operation to add.
         */
        public void apply(ImageOperation op) {
                //current = op.apply(current);
                ops.add(op);
        }

        /**
         * <p>
         * Undo the last {@link ImageOperation} added to the Macros.
         * </p>
         */
        public void undo() {

                /*
                 * This if loop change was made due to a funky error wherein if the
                 * user were to continuously press undo, it could fill the
                 * redoOps stack with nothing as it would push the nothing
                 * from the ops stack.
                 */
                if (ops.size() > 0) {
                        redoOps.push(ops.pop());
                }

        }

        /**
         * <p>
         * Readd the most recently {@link undo}ne {@link ImageOperation} to the macros.
         * </p>
         */
        public void redo() {
                apply(redoOps.pop());
        }

        public boolean isEmpty() {
                return !ops.isEmpty();
        }

        public int numRedoOps() {
                return redoOps.size();
        }

        public int numOps() {
                return ops.size();
        }
}

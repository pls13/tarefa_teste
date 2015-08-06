
package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 *
 * @author timi
 */
public class DocumentMaxLength extends PlainDocument{

    private int maxLength = 50;
   
    public DocumentMaxLength(){
    }
    
    public DocumentMaxLength(int maxLength){
        this.maxLength = maxLength;
    }
   
    @Override
    public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
        if (getLength() >= this.maxLength)
             return;
        super.insertString(offset, str, a);
    }
     
     
}




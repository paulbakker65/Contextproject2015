package operations.patterns;

import table.value.NumberValue;
import table.value.StringValue;
/**
 * Creating predetermined patterns.
 * @author paulbakker
 *
 */
public class PatternFactory {

 /**
  * The patterns that this factory can create.
  * @author paulbakker
  *
  */
 public enum PatternEnum {
   NORMAL_ENTRY, TWO_SENSOR_ONE_WEB, ACTION_DONE, TEST
 }
 
 private PatternEnum pe;
 
 public PatternFactory(PatternEnum pe) {
     this.pe = pe;
 }
 
 public Pattern getPattern() {
   switch(pe) {
     case NORMAL_ENTRY : {
        return nePattern();
     }
     case TWO_SENSOR_ONE_WEB : {
         return new SingleOccurrencePattern("Useless", nePattern());
     }
     case ACTION_DONE : {
         return adPattern();
     }
     default :
       return null;
   }
 }
 
 public Pattern nePattern() {
   Pattern endPattern = new SingleOccurrencePattern("CMI_id");
   Pattern prevPattern = new SingleOccurrencePattern("CMI_id", endPattern);
   
   for (int i = 0; i < 3; i++) {
     prevPattern = new SingleOccurrencePattern("CMI_id", prevPattern);
   }
   
   return new SingleOccurrencePattern("Useless", prevPattern);
 }
 
 public Pattern adPattern() {
   Pattern endPattern = new SingleOccurrenceValuePattern("Measurement", new StringValue("Kreatinine2 (stat)"));
   Pattern prevPattern = new SingleOccurrenceValuePattern("KAAI", new NumberValue(1), endPattern);
   
   for (int i = 0; i < 3; i++) {
     prevPattern = new SingleOccurrencePattern("CMI_id", prevPattern);
   }
   
   return new SingleOccurrencePattern("Useless", new SingleOccurrencePattern("Useless", prevPattern));
 }
 
  
  
  
}

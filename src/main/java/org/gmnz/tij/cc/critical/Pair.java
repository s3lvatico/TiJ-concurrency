package org.gmnz.tij.cc.critical;

/**
 * Una coppia di interi positivi.
 * <p>
 * Le componenti possono solo essere incrementate singolarmente. Ci sono metodi
 * per l'accesso in lettura alle componenti. (questo non rende thread safe questa classe)
 * 
 * Il controllo di stato viene effettuato controllando se le componenti sono
 * uguali. Una difformità causa un'eccezione.
 */
class Pair {

   private int x;
   private int y;




   Pair(int x, int y) {
      this.x = x;
      this.y = y;
   }




   Pair() {
      this(0, 0);
   }




   int getX() {
      return x;
   }




   int getY() {
      return y;
   }




   /** incrementa la sola componente X */
   void incrementX() {
      x++;
   }




   /** incrementa la sola componente Y */
   void incrementY() {
      y++;
   }




   public String toString() {
      return String.format("[%d, %d]", x, y);
   }

   public class PairValuesNotEqualException extends RuntimeException {
      private static final long serialVersionUID = 1L;




      public PairValuesNotEqualException() {
         super("Pair values not equal " + Pair.this);
      }
   }




   /**
    * Controlla lo stato.
    * 
    * Se le due componenti sono diverse viene lanciata una
    * {@link PairValuesNotEqualException}
    */
   void checkState() {
      if (x != y) {
         throw new PairValuesNotEqualException();
      }
   }
}

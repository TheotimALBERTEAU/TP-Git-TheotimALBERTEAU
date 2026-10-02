public class Inventaire {

  // Nombre d articles en stock
  private static int nbArticles = 0;

  public static int getNbArticles() {
    return nbArticles;
  }

  public static void main(String[] args) {
    System.out.println("Inventaire : " + nbArticles + " article(s)");
  }
}

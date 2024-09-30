import java.util.Scanner;

public class TicTacToe {


    public static class Board {  // Board sınıfını static yapıyoruz
        // 3x3 boyutunda bir tahta oluşturuyoruz (Tic-Tac-Toe oyunu için)


        private char[][] board;

        
        // Tahtanın boyutunu sabit olarak tanımlıyoruz (3x3'lük bir oyun tahtası)
        private static final int SIZE = 3;

    
        // Kurucu metot: Bu sınıfın nesnesi oluşturulduğunda çalışır ve tahta başlatılır
        public Board() {
            board = new char[SIZE][SIZE]; // 3x3'lük bir dizi oluşturuyoruz
            initializeBoard(); // Tahtayı boş karakterlerle başlatan metodu çağırıyoruz

        }
    
        // initializeBoard metodu tahtayı başlatır ve tüm hücrelere boş karakter (' ') koyar
        public void initializeBoard() {

            for (int i = 0; i < SIZE; i++) { // Satırları dolaşır (0, 1, 2)
                for (int j = 0; j < SIZE; j++) { // Her satırdaki sütunları dolaşır (0, 1, 2)
                    board[i][j] = ' '; // Her hücreye boş bir karakter koyar

                }
            }
        }
    
        // Bu metod tahtayı ekrana yazdırır
        public void printBoard() {

            for (int i = 0; i < SIZE; i++) { // Satırları dolaşır
                for (int j = 0; j < SIZE; j++) { // Her satırdaki sütunları dolaşır
                    System.out.print(board[i][j]); // Tahtadaki karakteri yazdırır (X, O veya boşluk)
                    if (j < SIZE - 1) System.out.print("|"); // Sütunlar arasına ayraç koyar

                }
                System.out.println(); // Satır sonuna geldiğinde yeni satıra geçer

                if (i < SIZE - 1) System.out.println("-----"); // Satırların arasına çizgi koyar

            }
        }
    
        // Bu metod oyuncunun hamlesini tahtaya işler
        // Eğer verilen satır ve sütun geçerliyse (boşsa), oyuncunun sembolünü yerleştirir
        public boolean makeMove(int row, int col, char player) {

            // Hamlenin geçerli olup olmadığını kontrol eder: Satır ve sütun sınırlar içinde mi, ve hücre boş mu?

            if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == ' ') {

                board[row][col] = player; // Eğer geçerliyse, oyuncunun sembolünü o hücreye koyar
                return true; // Hamle başarılı olduğunda true döndürür

            }

            return false; // Geçersiz hamlede false döner

        }
    
        // Bu metod belirtilen oyuncunun kazanıp kazanmadığını kontrol eder

        public boolean checkWin(char player) {

            // Satırları kontrol eder
            for (int i = 0; i < SIZE; i++) {

                if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {

                    return true; // Eğer bir satırdaki tüm hücreler aynı oyuncuya aitse, oyuncu kazanmış demektir

                }
            }
    
            // Sütunları kontrol eder

            for (int i = 0; i < SIZE; i++) {

                if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                    return true; // Eğer bir sütundaki tüm hücreler aynı oyuncuya aitse, oyuncu kazanmış demektir

                }
            }
    
            // Çaprazları kontrol eder (sol üstten sağ alta çapraz)

            if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {

                return true; // Eğer sol üstten sağ alta doğru olan çaprazda aynı oyuncunun sembolleri varsa, kazanmış demektir

            }
    
            // Diğer çaprazı kontrol eder (sağ üstten sol alta çapraz)

            if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {

                return true; // Eğer sağ üstten sol alta doğru olan çaprazda aynı oyuncunun sembolleri varsa, kazanmış demektir
            }
    
            return false; // Eğer yukarıdaki kontrollerin hiçbiri kazanma durumu yaratmadıysa, false döner
        }
    
        // Bu metod tahtanın tamamen dolup dolmadığını kontrol eder (beraberlik durumu için)

        public boolean isFull() {
            for (int i = 0; i < SIZE; i++) { // Tüm satırları dolaşır
                for (int j = 0; j < SIZE; j++) { // Tüm sütunları dolaşır
                   
                    if (board[i][j] == ' ') { // Eğer herhangi bir boş hücre varsa, tahta dolmamıştır
                        return false; // Boş hücre bulursa false döner
                    }
                }
            }
            return true; // Tüm hücreler doluysa true döner
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Yeni bir Board nesnesi oluşturuyoruz
        Board board = new Board();
        
        // Oyuncuları tanımlıyoruz
        char currentPlayer = 'X';
        boolean gameWon = false;
        boolean isBoardFull = false;

        // Oyun devam ederken
        while (!gameWon && !isBoardFull) {
            board.printBoard(); // Tahtayı ekrana yazdırıyoruz
            System.out.println("Sıra " + currentPlayer + " oyuncusunda. Hamleni yap (satır ve sütun): ");
            
            // Kullanıcıdan hamle alıyoruz (0-2 arası geçerli satır ve sütun)
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // Hamlenin geçerli olup olmadığını kontrol ediyoruz

            if (board.makeMove(row, col, currentPlayer)) {
                // Oyuncunun kazandı mı kontrol ediyoruz

                if (board.checkWin(currentPlayer)) {
                    board.printBoard(); // Son durumu yazdır

                    System.out.println("Oyuncu " + currentPlayer + " kazandı!");
                    gameWon = true;
                } else if (board.isFull()) { // Beraberlik kontrolü
                    
                    board.printBoard(); // Son durumu yazdır
                    System.out.println("Oyun berabere!");
                    isBoardFull = true;
                } else {
                    // Sıra değiştiriliyor
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Geçersiz hamle. Lütfen tekrar deneyin.");
            }
        }

        scanner.close(); 
    }
}

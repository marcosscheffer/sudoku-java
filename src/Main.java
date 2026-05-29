import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.Board;
import model.Space;
import util.BoardTemplate;

class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private static Board board;
    private final static int BOARD_LIMIT = 9;

    public static void main(String[] args) {
        final var position = Stream.of(args)
            .collect(Collectors.toMap(
                k -> k.split(";")[0],
                v -> v.split(";")[1]
            ));
        var option = -1;
        while (true) {
            System.out.println("Selecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo jogo");
            System.out.println("2 - Colocar um novo numero");
            System.out.println("3 - Remover um numero");
            System.out.println("4 - Vizualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = scanner.nextInt();

            switch (option) {
                case 1 -> startGame(position);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida, tente novamente");
            }
        }
    }

    private static void finishGame() {
        if (Objects.isNull(board)) {
            System.out.println("Jogo não iniciado, inicie um jogo para colocar um numero");
            return;
        }

        if (board.gameIsFinished()) {
            System.out.println("Parabéns, você finalizou o jogo com sucesso");
            showCurrentGame();
            board = null;
        } else if(board.hasError()){
            System.out.println("Seu jogo contem erros, corrija os erros para finalizar o jogo");
        }
    }

    private static void clearGame() {
        if (Objects.isNull(board)) {
            System.out.println("Jogo não iniciado, inicie um jogo para colocar um numero");
            return;
        }
        System.out.println("Tem certeza que deseja limpar o jogo?");
        var confirm = scanner.nextLine();
        while (!confirm.equalsIgnoreCase("sim") || !confirm.equalsIgnoreCase("nao")) {
            System.out.println("Informe sim ou nao");
            confirm = scanner.nextLine();
        }
        if (confirm.equalsIgnoreCase("sim")) {
            board.reset();
            System.out.println("Jogo limpo com sucesso");
        }
    }

    private static void showGameStatus() {
        if (Objects.isNull(board)) {
            System.out.println("Jogo não iniciado, inicie um jogo para colocar um numero");
            return;
        }

        System.out.printf("status do jogo %s\n", board.getGameStatus().getLabel());
        if (board.hasError()) {
            System.out.println("O jogo apresenta erros");
        } else {
            System.out.println("O jogo nao contem erros");
        }
    }

    private static void showCurrentGame() {
        if (Objects.isNull(board)) {
            System.out.println("Jogo não iniciado, inicie um jogo para colocar um numero");
            return;
        }

        var args = new Object[81];
        var argPos = 0;
        for (int i = 0; i < BOARD_LIMIT; i++) {
            for (var col: board.getSpaces()) {
                args[argPos ++] = " " + ((Objects.isNull(col.get(i).getActual())) ? " " : col.get(i).getActual());
            }
        }
        System.out.println("Seu jogo se encontra da seguinte forma");
        System.out.println(BoardTemplate.BOARD_TEMPLATE.formatted(args));
    }

    private static void removeNumber() {
        if (Objects.isNull(board)) {
            System.out.println("Jogo não iniciado, inicie um jogo para colocar um numero");
            return;
        }
        System.out.println("Informe a coluna em que o nomer sera inserido");
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha em que o nomer sera inserido");
        var row = runUntilGetValidNumber(0, 8);
        System.out.println("Informe o valor a ser inserido");
        var value = runUntilGetValidNumber(1, 9);
        if (!board.clearValue(col, row, value)) {
            System.out.println("Não foi possível inserir o numero, tente novamente");
        }
    }

    private static void inputNumber() {
        if (Objects.isNull(board)) {
            System.out.println("Jogo não iniciado, inicie um jogo para colocar um numero");
            return;
        }
        System.out.println("Informe a coluna em que o nomer sera inserido");
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha em que o nomer sera inserido");
        var row = runUntilGetValidNumber(0, 8);
        System.out.println("Informe o valor a ser inserido");
        var value = runUntilGetValidNumber(1, 9);
        if (!board.changeValue(col, row, value)) {
            System.out.println("Não foi possível inserir o numero, tente novamente");
        }
    }
    private static void startGame(Map<String, String> position) {
        if (Objects.nonNull(board)) {
            System.out.println("Jogo já iniciado, finalize o jogo atual para iniciar um novo");
            return;
        }
        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j =0; j < BOARD_LIMIT; j++) {
                var positioConfig = position.get("%s,%s".formatted(i,j));
                var expected = Integer.parseInt(positioConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positioConfig.split(",")[1]);
                var currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }

        board = new Board(spaces);
        System.out.println("Jogo iniciado com sucesso");
    }

    private static int runUntilGetValidNumber(final int min, final int max) {
        var current = scanner.nextInt();
        while (current < min || current > max) {
            System.out.println("Informe um numero entre %s e %s".formatted(min, max));
            current = scanner.nextInt();
        }
        return current;
    }
}
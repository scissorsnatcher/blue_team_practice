public class Test {

    public static void main(String[]args){


        Graph graph = new Graph();
        graph.initializeByFile("C:\\Users\\Павел\\IdeaProjects\\Boruvka1\\target\\entryGraph");

        BoruvkasAlgorithm boruvka = new BoruvkasAlgorithm();
        boruvka.boruvkaMST(graph);
    }
}
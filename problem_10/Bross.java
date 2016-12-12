// CollatzConjectureTree.java

package problem_10;

/**
 * Created by bross on 2016. 12. 10..
 */
public class CollatzConjectureTree {

    public CollatzConjectureTree leftSubTree;
    public CollatzConjectureTree rightSubTree;

    public long value;
    public int depth;
    private final int MAX_DEPTH;


    public CollatzConjectureTree(int depth) {
        MAX_DEPTH = depth;
        leftSubTree = null;
        rightSubTree = null;
        this.depth = 1;
        value = 1;
        rightSubTree = generateTree(2, this.depth + 1);
    }


    public CollatzConjectureTree(long value, int depth, int MAX_DEPTH) {
        this.value = value;
        this.depth = depth;
        this.MAX_DEPTH = MAX_DEPTH;
    }

    public CollatzConjectureTree generateTree(final long node, int depth) {
        if (0 < depth && depth <= MAX_DEPTH) {
            CollatzConjectureTree collatzConjectureTree = new CollatzConjectureTree(node, depth, MAX_DEPTH);
            if ((node - 1) % 3 == 0) {
                if (((node - 1) / 3) % 2 == 1 && (node - 1) / 3 != 1) {
                    collatzConjectureTree.leftSubTree = generateTree((node - 1) / 3, depth + 1);
                }
            }
            collatzConjectureTree.rightSubTree = generateTree(node * 2, depth + 1);

            return collatzConjectureTree;

        } else
            return null;
    }

    public int getDepth()
    {
        return depth;
    }

    public boolean isMoreOneValue()
    {
        return value > 1;
    }

}

// Problem10.java

package problem_10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bross on 2016. 12. 7..
 */
public class Problem10 {

    public static Stream<CollatzConjectureTree> postOrderStream(List<CollatzConjectureTree> collatzConjectureTreeList, CollatzConjectureTree collatzConjectureTree) {
        if (collatzConjectureTree != null) {
            postOrderStream(collatzConjectureTreeList, collatzConjectureTree.leftSubTree);
            postOrderStream(collatzConjectureTreeList, collatzConjectureTree.rightSubTree);
            collatzConjectureTreeList.add(collatzConjectureTree);
        }
        return collatzConjectureTreeList.stream();
    }

    public static Stream<CollatzConjectureTree> postOrderStream(CollatzConjectureTree collatzConjectureTree) {
        List<CollatzConjectureTree> collatzConjectureTrees = new ArrayList<>();
        return postOrderStream(collatzConjectureTrees, collatzConjectureTree);
    }

    public static void main(String[] args) {
        final int MAX_DPETH = 40;
        long start = System.currentTimeMillis();
        CollatzConjectureTree collatzConjectureTree = new CollatzConjectureTree(MAX_DPETH);
        postOrderStream(collatzConjectureTree).filter(CollatzConjectureTree::isMoreOneValue).collect(Collectors.groupingBy(CollatzConjectureTree::getDepth, Collectors.counting())).forEach((k, v) -> {
            System.out.print(v);
            if (k < MAX_DPETH) {
                System.out.print(", ");
            }
        });
        System.out.println("\ntime : " + (System.currentTimeMillis() - start));
    }

}

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.stream.Collectors;

public class ByungMinBaek {
	private static final int MAX = 40;

	public static void main(String[] args) throws IOException {
		CollatzNode root = new CollatzNode(1L, 1);
		root.stream()
				.filter(n -> n.val != 1)
				.collect(Collectors.groupingBy(CollatzNode::getDepth, Collectors.counting()))
				.forEach((k, v) -> {
					if (k < MAX) {
						System.out.print(v + ", ");
					} else {
						System.out.print(v);
					}
				});
	}

	public static class CollatzNode extends MockCollection {
		public long val = 1;
		public int depth = 1;

		public CollatzNode parent = null;
		public CollatzNode evenChild = null;
		public CollatzNode oddChild = null;

		public CollatzNode(long val, int depth) {
			this.val = val;
			this.depth = depth;
			if (depth < MAX) {
				this.oddChild = new CollatzNode(val * 2, depth + 1);
				if ((val - 1) % 3 == 0) {
					if (((val - 1) / 3) % 2 == 1 && val != 4) {
						this.evenChild = new CollatzNode((val - 1) / 3, depth + 1);
					}
				}
			}
		}

		public int getDepth() {
			return depth;
		}

		@Override
		public Iterator<CollatzNode> iterator() {
			return new Itr(this);
		}

		public static class Itr implements Iterator<CollatzNode> {
			private Stack<CollatzNode> stack = new Stack<CollatzNode>();
			private CollatzNode current;

			public Itr(CollatzNode argRoot) {
				current = argRoot;
			}

			public CollatzNode next() {
				while (current != null) {
					stack.push(current);
					current = current.evenChild;
				}

				current = stack.pop();
				CollatzNode node = current;
				current = current.oddChild;

				return node;
			}

			public boolean hasNext() {
				return stack.isEmpty() == false || current != null;
			}
		}
	}

	public static class MockCollection implements Collection<CollatzNode> {

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean contains(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator<CollatzNode> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean add(CollatzNode e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends CollatzNode> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
		}
	}
}

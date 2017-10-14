public class Stack<T> {




class Node<T> {
	T data;
	Node<T> next;
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}

	public String toString() {
		return data+"";
	}
}

	
	Node<T> front;
	int size;

	public Stack() {
		front = null;
		size = 0;
	}

	public void push(T item) {
		front = new Node<T>(item, front);
		size++;
	}

	public T pop() {
		if (front == null) {
			return null;
		}
		T temp = front.data;
		front = front.next;
		size--;
		return temp;
	}

	public boolean isEmpty() {
		return front == null;
	}

	public int size() {
		return size;
	}

	public void clear() {
		front = null;
		size = 0;
	}

}
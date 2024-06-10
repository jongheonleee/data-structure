package book.chapter02;

import java.util.NoSuchElementException;

// 원형 배열로 구현한 큐
public class ArrayQueue <E> {

    private E[] q;
    private int front, rear, size;

    public ArrayQueue() {
        q = (E[]) new Object[2]; // 크기 2인 배열로 초기화
        front = rear = size = 0;
    }

    public void add(E newItem) {
        if ((rear+1)%q.length == front) { // 큐가 다 찬경우
            resize(2*q.length);
        }

        rear = (rear+1) % q.length;
        q[rear] = newItem;
        size++;
    }

    public E remove() {
        if (isEmpty()) throw new NoSuchElementException();

        front = (front+1)%q.length;
        E item = q[front];
        q[front] = null;
        size--;

        if (size > 0 && size == q.length/4) {
            resize(q.length / 2);
        }

        return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newSize) { // 공간 newSize만큼 확보
        Object[] t = new Object[newSize];

        for (int i=1, j=front+1; i<size+1; i++, j++) {
            t[i] = q[j%q.length];
        }

        front = 0;
        rear = size;
        q = (E[]) t;
    }
}

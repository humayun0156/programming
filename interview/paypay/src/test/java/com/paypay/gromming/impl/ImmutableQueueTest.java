package com.paypay.gromming.impl;

import com.paypay.gromming.api.Queue;
import com.paypay.gromming.model.DummyModel;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;


public class ImmutableQueueTest {
    private DummyModel obj1;
    private DummyModel obj2;
    private Queue<DummyModel> target;

    @Before
    public void setup() {
        obj1 = new DummyModel("msg-1");
        obj2 = new DummyModel("msg-2");
        target = new ImmutableQueue<>();
    }

    @Test
    public void testEnqueueUnsupported() {
        Queue<DummyModel> queue = target.enQueue(obj1);
        assertThatThrownBy(() -> queue.enQueue(obj2)).isInstanceOf(UnsupportedOperationException.class) ;
    }

    @Test
    public void testDequeueUnsupported() {
        target.enQueue(obj1);
        Queue<DummyModel> queue = target.deQueue();
        assertThatThrownBy(queue::deQueue).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testEnqueue() {
        Queue<DummyModel> queue = target.enQueue(obj1);
        assertThat(queue.head()).isEqualTo(obj1);
    }

    @Test
    public void testDeque() {
        target.enQueue(obj1);
        target.enQueue(obj2);

        DummyModel obj21 = target.head();
        assertThat(obj1.getMsg()).isEqualTo(obj21.getMsg());
        target.deQueue();

        DummyModel obj22 = target.head();
        assertThat(obj22.getMsg()).isEqualTo(obj22.getMsg());
        target.deQueue();

        DummyModel obj23 = target.head();
        assertThat(obj23).isNull();
    }

    @Test
    public void headTestValue() {
        target.enQueue(obj1);
        DummyModel model = target.head();
        assertThat(model).isEqualTo(obj1);

        Queue<DummyModel> queue = target.enQueue(obj2);
        assertThat(queue.head()).isEqualTo(obj1);
    }

    @Test
    public void headTestNull() {
        DummyModel model = target.head();
        assertThat(model).isNull();

        Queue<DummyModel> queue = target.deQueue();
        assertThat(queue.head()).isNull();
    }

    @Test
    public void isEmptyTestFalse() {
        target.enQueue(obj1);
        assertThat(target.isEmpty()).isFalse();
    }

    @Test
    public void isEmptyTestTrue() {
        assertThat(target.isEmpty()).isTrue();
        target.enQueue(obj1);
        Queue<DummyModel> queue = target.deQueue();
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    public void dequeueHeadTest() {
        target.enQueue(obj1);
        target.enQueue(obj2);
        Queue<DummyModel> queue = target.deQueue();
        assertThat(queue.head()).isEqualTo(obj2);
    }

}
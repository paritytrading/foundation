/*
 * Copyright 2016 Foundation authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.paritytrading.foundation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ASCIITest {

    @Test
    void get() {
        byte[] bytes = new byte[] { 'f', 'o', 'o', ' ', ' ' };

        assertEquals("foo  ", ASCII.get(bytes));
    }

    @Test
    void getWithStringBuilder() {
        byte[] bytes = new byte[] { 'f', 'o', 'o', ' ', ' ' };

        StringBuilder b = new StringBuilder();

        ASCII.get(bytes, b);

        assertEquals("foo  ", b.toString());
    }

    @Test
    void put() {
        assertArrayEquals(new byte[] { 'f', 'o', 'o' }, ASCII.put("foo"));
    }

    @Test
    void putLeft() {
        byte[] bytes = new byte[5];

        ASCII.putLeft(bytes, "foo");

        assertArrayEquals(new byte[] { 'f', 'o', 'o', ' ', ' ' }, bytes);
    }

    @Test
    void putTooLongLeft() {
        byte[] bytes = new byte[2];

        assertThrows(IndexOutOfBoundsException.class, () -> ASCII.putLeft(bytes, "foo"));
    }

    @Test
    void putRight() {
        byte[] bytes = new byte[5];

        ASCII.putRight(bytes, "foo");

        assertArrayEquals(new byte[] { ' ', ' ', 'f', 'o', 'o' }, bytes);
    }

    @Test
    void putTooLongRight() {
        byte[] bytes = new byte[2];

        assertThrows(IndexOutOfBoundsException.class, () -> ASCII.putRight(bytes, "foo"));
    }

    @Test
    void getLongRight() {
        byte[] bytes = new byte[] { ' ', ' ', '1', '2', '3' };

        assertEquals(123, ASCII.getLong(bytes));
    }

    @Test
    void getNegativeLongRight() {
        byte[] bytes = new byte[] { ' ', '-', '1', '2', '3' };

        assertEquals(-123, ASCII.getLong(bytes));
    }

    @Test
    void getLongLeft() {
        byte[] bytes = new byte[] { '1', '2', '3', ' ', ' ' };

        assertEquals(123, ASCII.getLong(bytes));
    }

    @Test
    void getNegativeLongLeft() {
        byte[] bytes = new byte[] { '-', '1', '2', '3', ' ' };

        assertEquals(-123, ASCII.getLong(bytes));
    }

    @Test
    void putLongLeft() {
        byte[] bytes = new byte[5];

        ASCII.putLongLeft(bytes, 123);

        assertArrayEquals(new byte[] { '1', '2', '3', ' ', ' ' }, bytes);
    }

    @Test
    void putNegativeLongLeft() {
        byte[] bytes = new byte[5];

        ASCII.putLongLeft(bytes, -123);

        assertArrayEquals(new byte[] { '-', '1', '2', '3', ' ' }, bytes);
    }

    @Test
    void putTooLongLongLeft() {
        byte[] bytes = new byte[5];

        assertThrows(IndexOutOfBoundsException.class, () -> ASCII.putLongLeft(bytes, 123456));
    }

    @Test
    void putLongRight() {
        byte[] bytes = new byte[5];

        ASCII.putLongRight(bytes, 123);

        assertArrayEquals(new byte[] { ' ', ' ', '1', '2', '3' }, bytes);
    }

    @Test
    void putNegativeLongRight() {
        byte[] bytes = new byte[5];

        ASCII.putLongRight(bytes, -123);

        assertArrayEquals(new byte[] { ' ', '-', '1', '2', '3' }, bytes);
    }

    @Test
    void putTooLongLongRight() {
        byte[] bytes = new byte[5];

        assertThrows(IndexOutOfBoundsException.class, () -> ASCII.putLongRight(bytes, 123456));
    }

    @Test
    void getFixedLeft() {
        byte[] bytes = new byte[] { '1', '.', '2', '3', ' ' };

        assertEquals(123, ASCII.getFixed(bytes, 2));
    }

    @Test
    void getFixedLeftWithoutDecimalDigits() {
        byte[] bytes = new byte[] { '1', '2', '3', ' ', ' ' };

        assertEquals(12300, ASCII.getFixed(bytes, 2));
    }

    @Test
    void getFixedLeftWithFewerDecimalDigits() {
        byte[] bytes = new byte[] { '1', '2', '.', '3', ' ' };

        assertEquals(1230, ASCII.getFixed(bytes, 2));
    }

    @Test
    void getFixedLeftWithMoreDecimalDigits() {
        byte[] bytes = new byte[] { '1', '.', '2', '3', ' ' };

        assertEquals(12, ASCII.getFixed(bytes, 1));
    }

    @Test
    void getFixedRight() {
        byte[] bytes = new byte[] { ' ', '1', '.', '2', '3' };

        assertEquals(123, ASCII.getFixed(bytes, 2));
    }

    @Test
    void getFixedRightWithoutDecimalDigits() {
        byte[] bytes = new byte[] { ' ', ' ', '1', '2', '3' };

        assertEquals(12300, ASCII.getFixed(bytes, 2));
    }

    @Test
    void getFixedRightWithFewerDecimalDigits() {
        byte[] bytes = new byte[] { ' ', '1', '2', '.', '3' };

        assertEquals(1230, ASCII.getFixed(bytes, 2));
    }

    @Test
    void getFixedRightWithMoreDecimalDigits() {
        byte[] bytes = new byte[] { ' ', '1', '.', '2', '3' };

        assertEquals(12, ASCII.getFixed(bytes, 1));
    }

    @Test
    void getNegativeFixed() {
        byte[] bytes = new byte[] { '-', '1', '.', '2', '3' };

        assertEquals(-123, ASCII.getFixed(bytes, 2));
    }

    @Test
    void getNegativeFixedWithoutDecimalDigits() {
        byte[] bytes = new byte[] { ' ', '-', '1', '2', '3' };

        assertEquals(-12300, ASCII.getFixed(bytes, 2));
    }

    @Test
    void getNegativeFixedWithFewerDecimalDigits() {
        byte[] bytes = new byte[] { '-', '1', '2', '.', '3' };

        assertEquals(-1230, ASCII.getFixed(bytes, 2));
    }

    @Test
    void getNegativeFixedWithMoreDecimalDigits() {
        byte[] bytes = new byte[] { '-', '1', '.', '2', '3' };

        assertEquals(-12, ASCII.getFixed(bytes, 1));
    }

    @Test
    void putFixedLeft() {
        byte[] bytes = new byte[5];

        ASCII.putFixedLeft(bytes, 123, 2);

        assertArrayEquals(new byte[] { '1', '.', '2', '3', ' ' }, bytes);
    }

    @Test
    void putSmallFixedLeft() {
        byte[] bytes = new byte[5];

        ASCII.putFixedLeft(bytes, 1, 2);

        assertArrayEquals(new byte[] { '0', '.', '0', '1', ' ' }, bytes);
    }

    @Test
    void putNegativeFixedLeft() {
        byte[] bytes = new byte[5];

        ASCII.putFixedLeft(bytes, -123, 2);

        assertArrayEquals(new byte[] { '-', '1', '.', '2', '3' }, bytes);
    }

    @Test
    void putSmallNegativeFixedLeft() {
        byte[] bytes = new byte[5];

        ASCII.putFixedLeft(bytes, -1, 2);

        assertArrayEquals(new byte[] { '-', '0', '.', '0', '1' }, bytes);
    }

    @Test
    void putTooLongFixedLeft() {
        byte[] bytes = new byte[5];

        assertThrows(IndexOutOfBoundsException.class, () -> ASCII.putFixedLeft(bytes, 123456, 2));
    }

    @Test
    void putFixedRight() {
        byte[] bytes = new byte[5];

        ASCII.putFixedRight(bytes, 123, 2);

        assertArrayEquals(new byte[] { ' ', '1', '.', '2', '3' }, bytes);
    }

    @Test
    void putSmallFixedRight() {
        byte[] bytes = new byte[5];

        ASCII.putFixedRight(bytes, 1, 2);

        assertArrayEquals(new byte[] { ' ', '0', '.', '0', '1' }, bytes);
    }

    @Test
    void putNegativeFixedRight() {
        byte[] bytes = new byte[5];

        ASCII.putFixedRight(bytes, -123, 2);

        assertArrayEquals(new byte[] { '-', '1', '.', '2', '3' }, bytes);
    }

    @Test
    void putSmallNegativeFixedRight() {
        byte[] bytes = new byte[5];

        ASCII.putFixedRight(bytes, -1, 2);

        assertArrayEquals(new byte[] { '-', '0', '.', '0', '1' }, bytes);
    }

    @Test
    void putTooLongFixedRight() {
        byte[] bytes = new byte[5];

        assertThrows(IndexOutOfBoundsException.class, () -> ASCII.putFixedRight(bytes, 123456, 2));
    }

    @Test
    void packShort() {
        assertEquals(0x666f, ASCII.packShort("foo"));
    }

    @Test
    void packInt() {
        assertEquals(0x666f6f20, ASCII.packInt("foo"));
    }

    @Test
    void packLong() {
        assertEquals(0x666f6f2020202020L, ASCII.packLong("foo"));
    }

    @Test
    void unpackShort() {
        assertEquals("fo", ASCII.unpackShort((short)0x666f));
    }

    @Test
    void unpackShortWithStringBuilder() {
        StringBuilder b = new StringBuilder();

        ASCII.unpackShort((short)0x666f, b);

        assertEquals("fo", b.toString());
    }

    @Test
    void unpackInt() {
        assertEquals("foo ", ASCII.unpackInt(0x666f6f20));
    }

    @Test
    void unpackIntWithStringBuilder() {
        StringBuilder b = new StringBuilder();

        ASCII.unpackInt(0x666f6f20, b);

        assertEquals("foo ", b.toString());
    }

    @Test
    void unpackLong() {
        assertEquals("foo     ", ASCII.unpackLong(0x666f6f2020202020L));
    }

    @Test
    void unpackLongWithStringBuilder() {
        StringBuilder b = new StringBuilder();

        ASCII.unpackLong(0x666f6f2020202020L, b);

        assertEquals("foo     ", b.toString());
    }

}

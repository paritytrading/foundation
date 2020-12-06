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

class ByteArraysTest {

    @Test
    void reverseEvenLength() {
        byte[] a = new byte[] { 1, 2, 3, 4 };

        ByteArrays.reverse(a);

        assertArrayEquals(new byte[] { 4, 3, 2, 1 }, a);
    }

    @Test
    void reverseOddLength() {
        byte[] a = new byte[] { 1, 2, 3, 4, 5 };

        ByteArrays.reverse(a);

        assertArrayEquals(new byte[] { 5, 4, 3, 2, 1 }, a);
    }

    @Test
    void reverseEvenLengthRange() {
        byte[] a = new byte[] { 1, 2, 3, 4 };

        ByteArrays.reverse(a, 1, 3);

        assertArrayEquals(new byte[] { 1, 3, 2, 4 }, a);
    }

    @Test
    void reverseOddLengthRange() {
        byte[] a = new byte[] { 1, 2, 3, 4, 5 };

        ByteArrays.reverse(a, 1, 4);

        assertArrayEquals(new byte[] { 1, 4, 3, 2, 5 }, a);
    }

    @Test
    void packShort() {
        short s = ByteArrays.packShort(new byte[] { 0x01, 0x02, 0x03 }, (byte)0x00);

        assertEquals(0x0102, s);
    }

    @Test
    void packInt() {
        int i = ByteArrays.packInt(new byte[] { 0x01, 0x02, 0x03 }, (byte)0x00);

        assertEquals(0x01020300, i);
    }

    @Test
    void packLong() {
        long l = ByteArrays.packLong(new byte[] { 0x01, 0x02, 0x03, }, (byte)0x00);

        assertEquals(0x0102030000000000L, l);
    }

    @Test
    void unpackShort() {
        byte[] a = new byte[2];

        ByteArrays.unpackShort(a, (short)0x0102);

        assertArrayEquals(new byte[] { 0x01, 0x02 }, a);
    }

    @Test
    void unpackShortWithAllocation() {
        byte[] a = ByteArrays.unpackShort((short)0x0102);

        assertArrayEquals(new byte[] { 0x01, 0x02 }, a);
    }

    @Test
    void unpackInt() {
        byte[] a = new byte[4];

        ByteArrays.unpackInt(a, 0x01020300);

        assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x00 }, a);
    }

    @Test
    void unpackIntWithAllocation() {
        byte[] a = ByteArrays.unpackInt(0x01020300);

        assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x00 }, a);
    }

    @Test
    void unpackLong() {
        byte[] a = new byte[8];

        ByteArrays.unpackLong(a, 0x0102030000000000L);

        assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x00, 0x00 }, a);
    }

    @Test
    void unpackLongWithAllocation() {
        byte[] a = ByteArrays.unpackLong(0x0102030000000000L);

        assertArrayEquals(new byte[] { 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x00, 0x00 }, a);
    }

    @Test
    void shortRoundtrip() {
        short s = 0x01FF;

        assertEquals(s, ByteArrays.packShort(ByteArrays.unpackShort(s), (byte)0x00));
    }

    @Test
    void intRoundtrip() {
        int i = 0x017F81FF;

        assertEquals(i, ByteArrays.packInt(ByteArrays.unpackInt(i), (byte)0x00));
    }

    @Test
    void longRoundtrip() {
        long l = 0x01407F81C0FFL;

        assertEquals(l, ByteArrays.packLong(ByteArrays.unpackLong(l), (byte)0x00));
    }

}

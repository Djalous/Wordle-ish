/**
 *  Copyright 2024 SWE 2710 111 Team B (Duaa "DJ" Aljalous, Lazar Jovanovic,Theresa Kettner, Jack Rosenbecker)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class RecentHistory<T> {
    // Usage of Queue as suggested by ChatGPT 3.5 by OpenAI on 3/5/24
    private final Queue<T> data = new LinkedList<>();

    private final int maxLength;

    public RecentHistory(int maxLength) {
        this.maxLength = maxLength;
    }

    public void add(T item) {
        if (data.size() >= maxLength) {
            data.poll();
        }

        data.add(item);
    }

    public void clear() {
        data.clear();
    }

    /** Gets the last item in the history, removes it if remove is true.
     * Will not modify the history if remove is false.
     * @param remove Should we remove the last element?
     * @return The last element, null if the history is empty.
     */
    public T getLast(boolean remove) {
        if (remove) {
            return data.poll();
        }

        return data.peek();
    }


    /** Gets and removes the last element of the history. Equivalent to getLast(true)
     * @return Returns the last element of the history
     */
    public T getLast() {
        return getLast(true);
    }

    public Stream<T> stream() {
        return data.stream();
    }

    public T[] toArray(T[] a) {
        return data.toArray(a);
    }

    public Object[] toArray() {
        return data.toArray();
    }
}

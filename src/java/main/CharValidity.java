/**
 *  Copyright 2024 SWE 2710 111 Team B (Duaa "DJ" Aljalous, Lazar Jovanovic,Theresa Kettner, Jack Rosenbecker)
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package main;

public enum CharValidity {
    /**
     * When the character guessed is both the correct character
     * and is in the correct position in the word
     */
    CORRECT_POSITION,
    /**
     * When the character guessed is present in the word
     * but is not in the correct position
     */
    PRESENT_CHAR,
    /**
     * When the character guessed is not present in the word at all
     */
    INCORRECT,
}

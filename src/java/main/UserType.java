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

/**
 * A type enum for the type of user interacting with the program.
 * This will determine what functionality the user has access to.
 */
public enum UserType {
    /**
     * A regular user
     */
    USER("User"),
    MIDDLE_SCHOOL_STUDENT("Middle Schooler"),
    COLLEGE_STUDENT("High Schooler"),
    ADMIN("Admin"),
    TESTER("Tester");

    private final String user;

    UserType(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return user;
    }
}

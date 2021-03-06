/*
 * Copyright 2017 Richard.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jsonlogic;

import java.util.Map;

/**
 *
 * @author Richard
 */
public abstract class Node {

    abstract Result eval(Map<String, Result> data) throws EvaluationException;
    
    /**
     * If the result of calling eval is always the same regardless of the data parameter then this node is a constant.
     * @return 
     */
    abstract boolean isConstant();
    
    /**
     * reduce() should only be called if isContant() returns false
     */
    void reduce() throws EvaluationException {
    }

    abstract void treeToString(StringBuilder builder);

}

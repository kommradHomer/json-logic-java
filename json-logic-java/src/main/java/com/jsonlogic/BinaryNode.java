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

/**
 *
 * @author Richard
 */
abstract class BinaryNode extends Node {

    protected Node left;
    protected Node right;
    private final String operator;

    BinaryNode(Node left, Node right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    boolean isConstant() {
        return left.isConstant() && right.isConstant();
    }

    @Override
    void reduce() throws EvaluationException {
        if (left.isConstant()) {
            if (!(left instanceof ConstantNode)) {
                left = new ConstantNode(left.eval(null));
            }
        } else if (right.isConstant()) {
            if (!(right instanceof ConstantNode)) {
                right = new ConstantNode(right.eval(null));
            }
        }
    }

    @Override
    void treeToString(StringBuilder builder) {
//        builder.append("(");
//        left.treeToString(builder);
//        builder.append(operator);
//        right.treeToString(builder);
//        builder.append(")");

        builder.append("{\""+operator+"\":[");
        left.treeToString(builder);
        builder.append(",");
        right.treeToString(builder);
        builder.append("]}");

    }

}
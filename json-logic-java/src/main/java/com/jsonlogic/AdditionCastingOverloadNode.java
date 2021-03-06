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
class AdditionCastingOverloadNode extends Node {

    private Node node;

    AdditionCastingOverloadNode(Node node) {
        this.node = node;
    }

    @Override
    Result eval(Map<String, Result> data) throws EvaluationException {
//        StringBuilder builder = new StringBuilder();
//        node.treeToString(builder);
//        System.out.println("Before: " + builder.toString());
//        Result result = node.eval(data);
//        System.out.println("After: " + builder.toString());
//        return result;
        return tryCast(node,data);
    }

    @Override
    boolean isConstant() {
        return node.isConstant();
    }

    @Override
    void reduce() throws EvaluationException {
        if (!(node instanceof ConstantNode)) {
            node = new ConstantNode(node.eval(null));
        }
    }

    @Override
    void treeToString(StringBuilder builder) {
        builder.append("log (");
        node.treeToString(builder);
        builder.append(")");
    }

    private Result tryCast(Node node, Map<String, Result> data) throws EvaluationException {

        Result result = node.eval(data);

        if (result.isDouble())
            return result;

        if (result.isString())
        {
            try{
                return new Result(Double.parseDouble(result.getStringValue()));

            }catch (Exception ex){
                ex.printStackTrace();
            }

            throw new EvaluationException("");
        }

        return null;
    }

}

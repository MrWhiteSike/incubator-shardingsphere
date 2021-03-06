/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.sql.parser.visitor.impl;

import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.AlterLoginContext;
import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.AlterRoleContext;
import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.AlterUserContext;
import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.ClassPrivilegesClause_Context;
import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.ClassTypePrivilegesClause_Context;
import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.CreateLoginContext;
import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.CreateRoleContext;
import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.CreateUserContext;
import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.DenyContext;
import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.DropLoginContext;
import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.DropRoleContext;
import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.DropUserContext;
import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.GrantContext;
import org.apache.shardingsphere.sql.parser.autogen.SQLServerStatementParser.RevokeContext;
import org.apache.shardingsphere.sql.parser.sql.ASTNode;
import org.apache.shardingsphere.sql.parser.sql.segment.generic.TableSegment;
import org.apache.shardingsphere.sql.parser.sql.statement.dcl.AlterLoginStatement;
import org.apache.shardingsphere.sql.parser.sql.statement.dcl.AlterRoleStatement;
import org.apache.shardingsphere.sql.parser.sql.statement.dcl.AlterUserStatement;
import org.apache.shardingsphere.sql.parser.sql.statement.dcl.CreateLoginStatement;
import org.apache.shardingsphere.sql.parser.sql.statement.dcl.CreateRoleStatement;
import org.apache.shardingsphere.sql.parser.sql.statement.dcl.CreateUserStatement;
import org.apache.shardingsphere.sql.parser.sql.statement.dcl.DenyUserStatement;
import org.apache.shardingsphere.sql.parser.sql.statement.dcl.DropLoginStatement;
import org.apache.shardingsphere.sql.parser.sql.statement.dcl.DropRoleStatement;
import org.apache.shardingsphere.sql.parser.sql.statement.dcl.DropUserStatement;
import org.apache.shardingsphere.sql.parser.sql.statement.dcl.GrantStatement;
import org.apache.shardingsphere.sql.parser.sql.statement.dcl.RevokeStatement;
import org.apache.shardingsphere.sql.parser.visitor.SQLServerVisitor;

import java.util.Collection;
import java.util.Collections;

/**
 * SQLServer DCL visitor.
 *
 * @author zhangliang
 */
public final class SQLServerDCLVisitor extends SQLServerVisitor {
    
    @Override
    public ASTNode visitGrant(final GrantContext ctx) {
        GrantStatement result = new GrantStatement();
        if (null != ctx.classPrivilegesClause_()) {
            for (TableSegment each : getTableFromPrivilegeClause(ctx.classPrivilegesClause_())) {
                result.getAllSQLSegments().add(each);
                result.getTables().add(each);
            }
        }
        if (null != ctx.classTypePrivilegesClause_()) {
            for (TableSegment each : getTableFromPrivilegeClause(ctx.classTypePrivilegesClause_())) {
                result.getAllSQLSegments().add(each);
                result.getTables().add(each);
            }
        }
        return result;
    }
    
    @Override
    public ASTNode visitRevoke(final RevokeContext ctx) {
        RevokeStatement result = new RevokeStatement();
        if (null != ctx.classPrivilegesClause_()) {
            for (TableSegment each : getTableFromPrivilegeClause(ctx.classPrivilegesClause_())) {
                result.getAllSQLSegments().add(each);
                result.getTables().add(each);
            }
        }
        if (null != ctx.classTypePrivilegesClause_()) {
            for (TableSegment each : getTableFromPrivilegeClause(ctx.classTypePrivilegesClause_())) {
                result.getAllSQLSegments().add(each);
                result.getTables().add(each);
            }
        }
        return result;
    }
    
    private Collection<TableSegment> getTableFromPrivilegeClause(final ClassPrivilegesClause_Context ctx) {
        return null == ctx.onClassClause_().tableName() ? Collections.<TableSegment>emptyList() : Collections.singletonList((TableSegment) visit(ctx.onClassClause_().tableName()));
    }
    
    private Collection<TableSegment> getTableFromPrivilegeClause(final ClassTypePrivilegesClause_Context ctx) {
        return null == ctx.onClassTypeClause_().tableName() ? Collections.<TableSegment>emptyList() : Collections.singletonList((TableSegment) visit(ctx.onClassTypeClause_().tableName()));
    }
    
    @Override
    public ASTNode visitCreateUser(final CreateUserContext ctx) {
        return new CreateUserStatement();
    }
    
    @Override
    public ASTNode visitAlterUser(final AlterUserContext ctx) {
        return new AlterUserStatement();
    }
    
    @Override
    public ASTNode visitDeny(final DenyContext ctx) {
        DenyUserStatement result = new DenyUserStatement();
        if (null != ctx.classPrivilegesClause_()) {
            for (TableSegment each : getTableFromPrivilegeClause(ctx.classPrivilegesClause_())) {
                result.getAllSQLSegments().add(each);
                result.setTable(each);
            }
        }
        if (null != ctx.classTypePrivilegesClause_()) {
            for (TableSegment each : getTableFromPrivilegeClause(ctx.classTypePrivilegesClause_())) {
                result.getAllSQLSegments().add(each);
                result.setTable(each);
            }
        }
        return result;
    }
    
    @Override
    public ASTNode visitDropUser(final DropUserContext ctx) {
        return new DropUserStatement();
    }
    
    @Override
    public ASTNode visitCreateRole(final CreateRoleContext ctx) {
        return new CreateRoleStatement();
    }
    
    @Override
    public ASTNode visitAlterRole(final AlterRoleContext ctx) {
        return new AlterRoleStatement();
    }
    
    @Override
    public ASTNode visitDropRole(final DropRoleContext ctx) {
        return new DropRoleStatement();
    }
    
    @Override
    public ASTNode visitCreateLogin(final CreateLoginContext ctx) {
        return new CreateLoginStatement();
    }
    
    @Override
    public ASTNode visitAlterLogin(final AlterLoginContext ctx) {
        return new AlterLoginStatement();
    }
    
    @Override
    public ASTNode visitDropLogin(final DropLoginContext ctx) {
        return new DropLoginStatement();
    }
}

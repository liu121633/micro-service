<template>
    <el-container>
        <el-header>
            <el-form :inline="true" :model="pageWhere" class="demo-form-inline">
                <el-form-item label="库">
                    <el-select
                            v-model="pageWhere.where.tableSchemas"
                            multiple
                            collapse-tags
                            style="margin-left: 20px;;width: 300px"
                            placeholder="请选择">
                        <el-option
                                v-for="item in schemata"
                                :key="item.schemaName"
                                :label="item.schemaName"
                                :value="item.schemaName">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="表名">
                    <el-input v-model="pageWhere.where.tableName" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="注释">
                    <el-input v-model="pageWhere.where.tableComment" placeholder=""></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="pageSearch()">查询</el-button>
                </el-form-item>
            </el-form>
        </el-header>
        <el-main>
            <el-table :data="page.data"
                      v-loading="loading"
                      element-loading-text="拼命加载中"
                      element-loading-spinner="el-icon-loading"
                      element-loading-background="rgba(0, 0, 0, 0.8)"
                      :height="table.height"
                      custom
                      @sort-change="pageSearchSort"
                      style="width: 100%;" max-height="600" stripe border>
                <el-table-column type="index"></el-table-column>
                <el-table-column prop="tableSchema" sortable='custom' label="库"></el-table-column>
                <el-table-column prop="tableName" label="表名"></el-table-column>
                <el-table-column prop="tableType" sortable='custom' label="类型"></el-table-column>
                <el-table-column prop="tableComment" label="注释"></el-table-column>
                <el-table-column prop="tableCollation" label="字符集"></el-table-column>
                <el-table-column prop="createTime" sortable='custom' label="创建时间"></el-table-column>
                <el-table-column label="可选">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                @click="toInfo(scope.$index, scope.row)">操作
                        </el-button>

                    </template>
                </el-table-column>
            </el-table>
        </el-main>
        <el-footer>
            <div style="float:right;">
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :page-sizes="[10, 20, 30, 40, 50]"
                        layout="total, sizes, prev, pager, next, jumper"
                        :page-size="pageWhere.pageSize"
                        :total="page.total">
                </el-pagination>
            </div>
        </el-footer>
    </el-container>
</template>


<script>
    export default {
        name: "Tales",
        mounted() {
            this.fetch.get(this, "/schemata/search", res => {
                this.schemata = res.body;
            });
            this.pageSearch();
        },
        data() {
            return {
                schemata: {},
                table: {
                    height: 600
                },
                loading: true,
                pageWhere: {
                    pageNum: 1,
                    pageSize: 10,
                    orderBy: {
                        prop: "",
                        order: ""
                    },
                    where: {
                        tableSchemas: [],
                        tableName: '',
                        tableComment: '',
                    }
                },
                page: {
                    data: [],
                    endRow: null,
                    pageNum: 1,
                    pageSize: 10,
                    pages: null,
                    startRow: null,
                    total: null
                }
            }
        },
        methods: {
            toInfo: function (a, c) {
                this.$router.push(
                    {
                        name: 'table_info',
                        params: {
                            tableSchema: c.tableSchema,
                            tableName: c.tableName
                        }
                    }
                );
            },
            handleSizeChange(val) {
                this.pageWhere.pageSize = val;
                this.pageSearch();
            },
            handleCurrentChange(val) {
                this.pageWhere.pageNum = val;
                this.pageSearch();
            },
            pageSearchSort: function (orderBy) {
                this.pageWhere.orderBy.order = orderBy.order;
                this.pageWhere.orderBy.prop = orderBy.prop;
                this.pageSearch();
            },
            pageSearch: function () {
                this.loading = this;
                this.fetch.post(this, "/tables/search", this.pageWhere, res => {
                    this.page = res.body;
                });
                this.loading = false;
            }
        }
    }
</script>

<style scoped>

</style>
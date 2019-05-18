<template>
    <div>
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item ref="table">
                    <el-table :data="table.data"
                              v-loading="loading"
                              element-loading-text="拼命加载中"
                              element-loading-spinner="el-icon-loading"
                              element-loading-background="rgba(0, 0, 0, 0.8)"
                              :height="table.height"
                              @selection-change="handleSelectionChange"
                              custom
                              @sort-change="pageSearchSort"
                              style="width: 100%;" max-height="600" stripe border>
                        <el-table-column type="selection"></el-table-column>
                        <el-table-column type="index"></el-table-column>
                        <el-table-column prop='tableSchema' label='库'></el-table-column>
                        <el-table-column prop='tableName' label='表名'></el-table-column>
                        <el-table-column prop='columnName' label='列名'></el-table-column>
                        <el-table-column prop='fieldName' label='实体建议名称(驼峰)'></el-table-column>
                        <el-table-column prop='columnKey' label='键类型'></el-table-column>
                        <el-table-column prop='columnType' label='列类型'></el-table-column>
                        <el-table-column prop='javaType' label='java类型'></el-table-column>
                        <el-table-column prop='columnComment' label='列注释'></el-table-column>
                        <el-table-column prop='isNullable' label='允许null吗'></el-table-column>
                        <el-table-column prop='characterSetName' label='字符集名称'></el-table-column>
                        <el-table-column prop='collationName' label='校对规则名称'></el-table-column>
                        <el-table-column prop='columnDefault' label='列默认值'></el-table-column>
                    </el-table>
                </el-form-item>
                <el-form-item>
                    <el-form ref="form" :model="form">
                        <el-form-item label="className"
                                      prop="className"
                                      :rules="[{ required: true, message: 'className不能为空'}]">
                            <el-input required="true" v-model="form.className" placeholder="className"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="subGenerate(form)">生成代码</el-button>
                        </el-form-item>
                        <codemirror v-model="generateCode" ></codemirror>
                    </el-form>
                </el-form-item>
            </el-form>
    </div>
</template>

<script>
    export default {
        name: "TablesInfo",
        mounted: function () {
            this.params.tableSchema = this.$route.params.tableSchema;
            this.params.tableName = this.$route.params.tableName;
            this.form.className = this.$route.params.tableName;
            this.loading = true;
            this.fetch.get(this,
                "/columns/" + this.params.tableSchema + "/" + this.params.tableName,
                res => {
                    this.table.data = res.body;
                    this.loading = false;
                }
            )
        },
        methods: {
            subGenerate: function (form) {
                console.info(form);
                if (this.generateData.length > 0) {
                    this.fetch.post(
                        this,
                        "/generate/" + this.form.className,
                        this.generateData,
                        res => {
                            this.$message({
                                message: '代码生成成功',
                                type: 'success'
                            });
                            this.generateCode = res.body;
                        })
                } else {
                    this.$message.error('请先选择数据');
                }
            },
            handleSelectionChange: function (val) {
                const generateData = [];
                for (const i in val) {
                    generateData.push({
                        fieldName: val[i].fieldName,
                        columnComment: val[i].columnComment,
                        javaType: val[i].javaType
                    });
                }
                this.generateData = generateData;
            },
            pageSearchSort: function (orderBy) {
                this.pageWhere.orderBy.order = orderBy.order;
                this.pageWhere.orderBy.prop = orderBy.prop;
                this.pageSearch();
            }
        },
        data: function () {
            return {
                generateCode: 'hello world!!',
                form: {
                    className: ''
                },
                generateData: [],
                params: {
                    tableSchema: '',
                    tableName: ''
                },
                loading: true,
                table: {
                    data: []
                }
            };
        }
    }
</script>

<style scoped>
    * {
        font-size: 10px;
    }
</style>
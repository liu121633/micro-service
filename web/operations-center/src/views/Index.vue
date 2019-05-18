<template>
    <div>
        <el-container id="app">
            <el-header id="header">
            </el-header>
            <el-container>
                <el-aside style="width: 200px;height:100%;">
                    <el-menu background-color="rgb(255, 255, 255)" style="height:100%;" router>
                        <el-submenu index="1">
                            <template slot="title">
                                <span>代码生成</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="/tables">数据库表</el-menu-item>
                                <el-menu-item index="/columns">数据库列</el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main>
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Index"
    }
</script>

<style>
    #app {
        height: 100%;
        width: 100%;
        position: absolute
    }

    #header {
        background-color: rgb(153, 154, 157);
        border-radius: 1px;
        padding: 0;
        box-shadow: inset 1px 2px 1px 21px rgb(153, 154, 157);
    }
</style>
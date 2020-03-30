<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${package.Mapper}.${entity}Mapper" >
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}" >
    <#list table.commonFields as item>
        <#if item.keyFlag == true >
        <id column="${item.name}" property="${item.propertyName}" />
        </#if>
        <#if item.keyFlag == false >
        <result column="${item.name}" property="${item.propertyName}"/>
        </#if>
    </#list>
        <#list table.fields as item>
        <result column="${item.name}" property="${item.propertyName}"/>
        </#list>
    </resultMap>
</mapper>
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
				xmlns:pom="http://maven.apache.org/POM/4.0.0"
>

	<xsl:template match="/">
		<html>
			<body>
				<h2>Dependency overview for:</h2>
				<div>
				<xsl:value-of select="pom:project/pom:groupId" />:<xsl:value-of select="pom:project/pom:artifactId" />:<xsl:value-of select="pom:project/pom:version" />
				</div>
				<h2>Dependencies</h2>
				<xsl:apply-templates  select="pom:project/pom:dependencies" />
				<h2>dependencyManagement</h2>
				<xsl:apply-templates  select="pom:project/pom:dependencyManagement" />
			</body>
		</html>
	</xsl:template>

	<xsl:template match="pom:project" ><xsl:apply-templates /></xsl:template>
	<xsl:template match="pom:modelVersion" ></xsl:template>
	<xsl:template match="pom:packaging" >T</xsl:template>
	<xsl:template match="pom:name" >T</xsl:template>
	<xsl:template match="pom:description" >T</xsl:template>
	<xsl:template match="pom:properties" >T</xsl:template>
	<xsl:template match="pom:build" >T</xsl:template>
	<xsl:template match="pom:profiles" ><xsl:value-of select="." /></xsl:template>
	<xsl:template match="pom:repositories" ><xsl:value-of select="." /></xsl:template>
	<xsl:template match="pom:dependencyManagement" ><xsl:apply-templates /></xsl:template>
	<xsl:template match="pom:dependencies" >
		<table>
			<thead>
				<tr>
					<th>groupId</th>
					<th>artifactId</th>
					<th>version</th>
				</tr>
			</thead>
			<tbody>
				<xsl:apply-templates />
			</tbody>
		</table>
	</xsl:template>
	<xsl:template match="pom:dependency" >
		<tr>
			<td><xsl:value-of select="./pom:groupId" /></td>
			<td><xsl:value-of select="./pom:artifactId" /></td>
			<td><xsl:value-of select="./pom:version" /></td>
		</tr>
	</xsl:template>
	<xsl:template match="text()" ></xsl:template>

</xsl:stylesheet>


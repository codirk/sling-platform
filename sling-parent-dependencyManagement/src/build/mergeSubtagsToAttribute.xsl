<xsl:stylesheet version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:output omit-xml-declaration="yes" indent="yes"/>
 <xsl:strip-space elements="*"/>

 <xsl:template match="node()|@*">
  <xsl:copy>
   <xsl:apply-templates select="node()|@*"/>
  </xsl:copy>
 </xsl:template>

 <xsl:template match="*">
  <xsl:copy>
   <xsl:apply-templates select="@*"/>
   <xsl:apply-templates mode="merge-components" select="*[starts-with(name(), 'merge-components')]"/>
   <xsl:apply-templates/>
  </xsl:copy>
 </xsl:template>

 <xsl:template mode="merge-components" match="*[starts-with(name(), 'merge-components')]">
   <xsl:attribute name="components">[<xsl:for-each select="component/."><xsl:value-of select="translate(normalize-space(.), ' ', '')"/><xsl:if test="position() != last()"><xsl:text>,</xsl:text></xsl:if></xsl:for-each>]</xsl:attribute>
 </xsl:template>

 <xsl:template match="*[starts-with(name(), 'merge-components')]"/>
</xsl:stylesheet>
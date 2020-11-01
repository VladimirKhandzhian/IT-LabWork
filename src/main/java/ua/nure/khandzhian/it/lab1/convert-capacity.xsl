<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template name="convert_capacity">
        <xsl:param name="capacity" select="0"/>
        <xsl:param name="capacity-value" select="0"/>
        <xsl:choose>
            <xsl:when test="$capacity='mAmper'"><xsl:value-of select="$capacity-value * 0.001"/></xsl:when>
            <xsl:when test="$capacity='Amper'"><xsl:value-of select="$capacity-value"/></xsl:when>
            <xsl:when test="$capacity='kAmper'"><xsl:value-of select="$capacity-value * 1000"/></xsl:when>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>
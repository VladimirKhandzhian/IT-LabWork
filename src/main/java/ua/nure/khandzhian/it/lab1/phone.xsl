<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ITLB1="http://nure.ua/khandzhian/ITLB1">
    <xsl:import href="convert-capacity.xsl"/>
    <xsl:template match="/">
        <html>
            <body>
                <table border="2" align="center">
                    <tr align="center">
                        <td>#</td>
                        <td>PhoneIndex</td>
                        <td>Model</td>
                        <td>Diagonal</td>
                        <td>NumberOfCamera</td>
                        <td>BatteryCapacity, Amper</td>
                        <td>Price, UAH</td>
                        <td>Description</td>
                        <td>Special Function</td>
                    </tr>
                    <xsl:for-each select="ITLB1:Phones/Phone">
                        <tr align="center">
                            <td><xsl:number value="position()" format="1"/></td>
                            <td><xsl:value-of select="PhoneIndex"/></td>
                            <td><xsl:value-of select="Model"/></td>
                            <td><xsl:value-of select="Parameters/Diagonal"/></td>
                            <td><xsl:value-of select="Parameters/NumberOfCamera"/>
                                <xsl:choose>
                                    <xsl:when test="Parameters/NumberOfCamera &lt; 3">(little)</xsl:when>
                                    <xsl:when test="Parameters/NumberOfCamera &lt; 4">(medium)</xsl:when>
                                    <xsl:otherwise>(a lot)</xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td>
                                <xsl:call-template name="convert_capacity">
                                    <xsl:with-param name="capacity" select="Parameters/BatteryCapacity/@capacity"/>
                                    <xsl:with-param name="capacity-value" select="Parameters/BatteryCapacity"/>
                                </xsl:call-template>
                            </td>
                            <td>
                                <xsl:if test="Price/@currency='EUR'">
                                    <xsl:value-of select="Price * 33.00"/> &#160;
                                </xsl:if>
                                <xsl:if test="Price/@currency='PLN'">
                                    <xsl:value-of select="Price * 7.00"/> &#160;
                                </xsl:if>
                                <xsl:if test="Price/@currency='UAH'">
                                    <xsl:value-of select="Price"/> &#160;
                                </xsl:if>
                            </td>
                            <td><xsl:value-of select="Description"/></td>
                            <td><xsl:value-of select="SpecialFunction"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ITLB1="http://nure.ua/khandzhian/ITLB1">

    <xsl:template match="/">
        <html>
            <body>
                <table border="2" align="center">
                    <tr align="center">
                        <td>PhoneIndex</td>
                        <td>Model</td>
                        <td>Diagonal</td>
                        <td>NumberOfCamera</td>
                        <td>BatteryCapacity</td>
                        <td>Price</td>
                        <td>Description</td>
                        <td>Special Function</td>
                    </tr>
                    <xsl:for-each select="ITLB1:Phones/Phone">
                        <tr align="center">
                            <td><xsl:value-of select="PhoneIndex"/></td>
                            <td><xsl:value-of select="Model"/></td>
                            <td><xsl:value-of select="Parameters/Diagonal"/></td>
                            <td><xsl:value-of select="Parameters/NumberOfCamera"/></td>
                            <td><xsl:value-of select="Parameters/BatteryCapacity"/>&#160;
                                <xsl:value-of select="Parameters/BatteryCapacity/@capacity"/></td>
                            <td><xsl:value-of select="Price"/>&#160;
                                <xsl:value-of select="Price/@currency"/></td>
                            <td><xsl:value-of select="Description"/></td>
                            <td><xsl:value-of select="SpecialFunction"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
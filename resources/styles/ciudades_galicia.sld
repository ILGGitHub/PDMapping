<?xml version="1.0" encoding="ISO-8859-1"?>
<StyledLayerDescriptor version="1.0.0" 
 xsi:schemaLocation="http://www.opengis.net/sld StyledLayerDescriptor.xsd" 
 xmlns="http://www.opengis.net/sld" 
 xmlns:ogc="http://www.opengis.net/ogc" 
 xmlns:xlink="http://www.w3.org/1999/xlink" 
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <!-- a Named Layer is the basic building block of an SLD document -->
  <NamedLayer>
    <Name>default_point</Name>
    <UserStyle>
    <!-- Styles can have names, titles and abstracts -->
      <Title>Default Point</Title>
      <Abstract>A sample style that draws a point</Abstract>
      <!-- FeatureTypeStyles describe how to render different features -->
      <!-- A FeatureTypeStyle for rendering points -->
      <FeatureTypeStyle>
        <Rule>
          <Name>rule1</Name>
          <Title>Red Square</Title>          
            <PointSymbolizer>
              <Graphic>
                <Mark>
                  <WellKnownName>circle</WellKnownName>
                  <Fill>
                    <CssParameter name="fill">#000000</CssParameter>
                  </Fill>
                </Mark>
              <Size>8</Size>
            </Graphic>
          </PointSymbolizer>
       <TextSymbolizer>
         <Label>
           <ogc:PropertyName>NOME</ogc:PropertyName>
         </Label>
         <Font>
           <CssParameter name="font-family">Arial</CssParameter>
           <CssParameter name="font-size">12</CssParameter>
           <CssParameter name="font-style">normal</CssParameter>
         </Font>
         <LabelPlacement>
           <PointPlacement>
             <AnchorPoint>
               <AnchorPointX>0.5</AnchorPointX>
               <AnchorPointY>0.5</AnchorPointY>
             </AnchorPoint>
             <Displacement>
               <DisplacementX>0</DisplacementX>
               <DisplacementY>-10</DisplacementY>
             </Displacement>             
           </PointPlacement>
         </LabelPlacement>
         <Fill>
           <CssParameter name="fill">#000000</CssParameter>
         </Fill>
		<Halo>
           <Radius>3</Radius>
           <Fill>
             <CssParameter name="fill">#FFFFFF</CssParameter>
           </Fill>
         </Halo>         
       </TextSymbolizer>          
        </Rule>
      </FeatureTypeStyle>
    </UserStyle>
  </NamedLayer>
</StyledLayerDescriptor>
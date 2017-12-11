package com.vp.plugin.sample.classdiagram.actions;

import java.awt.Point;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.DiagramManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IClassDiagramUIModel;
import com.vp.plugin.diagram.connector.IAssociationUIModel;
import com.vp.plugin.diagram.shape.IClassUIModel;
import com.vp.plugin.diagram.shape.IPackageUIModel;
import com.vp.plugin.model.IAssociation;
import com.vp.plugin.model.IAssociationClass;
import com.vp.plugin.model.IAssociationEnd;
import com.vp.plugin.model.IAttribute;
import com.vp.plugin.model.IClass;
import com.vp.plugin.model.IDataType;
import com.vp.plugin.model.IGeneralization;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.model.IOperation;
import com.vp.plugin.model.IPackage;
import com.vp.plugin.model.IParameter;
import com.vp.plugin.model.IProject;
import com.vp.plugin.model.IRealization;
import com.vp.plugin.model.factory.IModelElementFactory;

public class ClassDiagramActionControl implements VPActionController {
	
	private IDataType typeInt = null;
	private IDataType typeString = null;
	private IDataType typeBoolean = null;
	private IDataType typeVoid = null;
	
	@Override
	public void performAction(VPAction arg0) {
		loadDataType();
		
		// create blank class diagram
		DiagramManager diagramManager = ApplicationManager.instance().getDiagramManager();
		IClassDiagramUIModel diagram = (IClassDiagramUIModel) diagramManager.createDiagram(DiagramManager.DIAGRAM_TYPE_CLASS_DIAGRAM);
			
		// create base package model
		IPackage basePackage = IModelElementFactory.instance().createPackage();
		basePackage.setName("Base Package");
		// create base package shape
		IPackageUIModel basePackageShape = (IPackageUIModel) diagramManager.createDiagramElement(diagram, basePackage);
		basePackageShape.setBounds(94, 79, 717, 516);
		// set to automatic calculate the initial caption position
		basePackageShape.setRequestResetCaption(true);
		
		// create superclass model
		IClass superClass = IModelElementFactory.instance().createClass();
		superClass.setName("SuperClass");
		basePackage.addChild(superClass);
		
		// create attribute for superclass model
		IAttribute attribute = IModelElementFactory.instance().createAttribute();
		attribute.setName("superClassAttribute");
		attribute.setType(typeInt);
		attribute.setVisibility(IAttribute.VISIBILITY_PRIVATE);
		superClass.addAttribute(attribute);
		
		// create operation for superclass model
		IOperation superclassOperation = IModelElementFactory.instance().createOperation();
		superclassOperation.setName("superClassOperation");
		superclassOperation.setVisibility(IOperation.VISIBILITY_PUBLIC);
		superclassOperation.setReturnType(typeBoolean);
		
		// define parameters for operation
		IParameter parameter1 = IModelElementFactory.instance().createParameter();
		parameter1.setName("param1");
		parameter1.setType(typeString);
		superclassOperation.addParameter(parameter1);
		
		IParameter parameter2 = IModelElementFactory.instance().createParameter();
		parameter2.setName("param2");
		parameter2.setType(typeInt);
		superclassOperation.addParameter(parameter2);
		
		superClass.addOperation(superclassOperation);
		
		// create superclass shape
		IClassUIModel superClassShape = (IClassUIModel) diagramManager.createDiagramElement(diagram, superClass);
		superClassShape.setBounds(208, 144, 274, 57);
		basePackageShape.addChild(superClassShape);
		superClassShape.setRequestResetCaption(true);
		
		// create interface class model
		IClass interfaceClass = IModelElementFactory.instance().createClass();
		interfaceClass.setName("InterfaceClass");
		basePackage.addChild(interfaceClass);
		// specify the stereotype as "Interface"
		interfaceClass.addStereotype("Interface");
		
		// create operation for interface class
		IOperation interfaceOperation = IModelElementFactory.instance().createOperation();
		interfaceOperation.setName("interfaceOperation");
		interfaceOperation.setVisibility(IOperation.VISIBILITY_PUBLIC);
		interfaceOperation.setReturnType(typeVoid);		
		interfaceClass.addOperation(interfaceOperation);
		
		// create interface class shape
		IClassUIModel interfaceClassShape = (IClassUIModel) diagramManager.createDiagramElement(diagram, interfaceClass);
		interfaceClassShape.setBounds(514, 144, 141, 53);
		basePackageShape.addChild(interfaceClassShape);
		interfaceClassShape.setRequestResetCaption(true);		
		
		// create subclass model
		IClass subClass = IModelElementFactory.instance().createClass();
		subClass.setName("SubClass");
		basePackage.addChild(subClass);
		IClassUIModel subClassShape = (IClassUIModel) diagramManager.createDiagramElement(diagram, subClass);
		subClassShape.setBounds(208, 294, 272, 53);
		// set subclass to show inherited operations
		subClassShape.setShowDerivedOperations(true);
		basePackageShape.addChild(subClassShape);
		subClassShape.setRequestResetCaption(true);
		
		// create a normal class which will have normal association to subclass
		IClass classWithAssociation = IModelElementFactory.instance().createClass();
		classWithAssociation.setName("ClassWithAssociation");
		basePackage.addChild(classWithAssociation);
		IClassUIModel classWithAssociationShape = (IClassUIModel) diagramManager.createDiagramElement(diagram, classWithAssociation);
		classWithAssociationShape.setBounds(632, 294, 126, 40);
		basePackageShape.addChild(classWithAssociationShape);
		classWithAssociationShape.setRequestResetCaption(true);
		
		// create a normal class which will have aggregation association to subclass
		IClass aggregrationClass = IModelElementFactory.instance().createClass();
		aggregrationClass.setName("AggregationClass");
		basePackage.addChild(aggregrationClass);
		IClassUIModel aggregrationClassShape = (IClassUIModel) diagramManager.createDiagramElement(diagram, aggregrationClass);
		aggregrationClassShape.setBounds(143, 521, 128, 40);
		basePackageShape.addChild(aggregrationClassShape);
		aggregrationClassShape.setRequestResetCaption(true);
		
		// create a normal class which will have composition association to subclass
		IClass compositionClass = IModelElementFactory.instance().createClass();
		compositionClass.setName("CompositionClass");
		basePackage.addChild(compositionClass);
		IClassUIModel compositionClassShape = (IClassUIModel) diagramManager.createDiagramElement(diagram, compositionClass);
		compositionClassShape.setBounds(271, 443, 131, 40);
		basePackageShape.addChild(compositionClassShape);
		compositionClassShape.setRequestResetCaption(true);
		
		// create an association class
		IClass associationClass = IModelElementFactory.instance().createClass();
		associationClass.setName("AssociationClass");
		basePackage.addChild(associationClass);
		IClassUIModel associationClassShape = (IClassUIModel) diagramManager.createDiagramElement(diagram, associationClass);
		associationClassShape.setBounds(492, 384, 102, 40);
		basePackageShape.addChild(associationClassShape);
		associationClassShape.setRequestResetCaption(true);
		
		// create generalization relationship from superclass to subclass
		IGeneralization generalizationModel = IModelElementFactory.instance().createGeneralization();
		generalizationModel.setFrom(superClass); 
		generalizationModel.setTo(subClass);
		// create generalization connector on diagram
		diagramManager.createConnector(diagram, generalizationModel, superClassShape, subClassShape, null);
		
		// create realization relationship from interface class to subclass
		IRealization realizationModel = IModelElementFactory.instance().createRealization();
		realizationModel.setFrom(interfaceClass);
		realizationModel.setTo(subClass);
		// create realization connector on diagram
		diagramManager.createConnector(diagram, realizationModel, interfaceClassShape, subClassShape, null);
		
		// create normal association between subclass to "ClassWithAssociation"
		IAssociation associationModel = IModelElementFactory.instance().createAssociation();
		associationModel.setName("AssociationClass");
		associationModel.setFrom(subClass);
		associationModel.setTo(classWithAssociation);
		// specify multiplicity for from & to end 
		IAssociationEnd associationFromEnd = (IAssociationEnd) associationModel.getFromEnd();
		associationFromEnd.setMultiplicity("*");
		IAssociationEnd associationToEnd = (IAssociationEnd) associationModel.getToEnd();
		associationToEnd.setMultiplicity("*");	
		// create association connector on diagram
		IAssociationUIModel associationConnector = (IAssociationUIModel) diagramManager.createConnector(diagram, associationModel, subClassShape, classWithAssociationShape, null);
		// set to automatic calculate the initial caption position	
		associationConnector.setRequestResetCaption(true);
		
		// create aggregation association between subclass and AggregationClass
		IAssociation aggregationModel = IModelElementFactory.instance().createAssociation();
		aggregationModel.setFrom(subClass);
		aggregationModel.setTo(aggregrationClass);
		IAssociationEnd aggregationFromEnd = (IAssociationEnd) aggregationModel.getFromEnd();
		// specify from end as aggregation as well as the multiplicity
		aggregationFromEnd.setAggregationKind(IAssociationEnd.AGGREGATION_KIND_AGGREGATION);
		aggregationFromEnd.setMultiplicity("1");
		aggregationFromEnd.setName("subclass");
		// specify details for to end.
		IAssociationEnd aggregationToEnd = (IAssociationEnd) aggregationModel.getToEnd();
		aggregationToEnd.setMultiplicity("0..*");
		aggregationToEnd.setName("aggregation");
		// create aggregation connector on diagram
		IAssociationUIModel aggregationConnector = (IAssociationUIModel) diagramManager.createConnector(diagram, aggregationModel, subClassShape, aggregrationClassShape, null);
		aggregationConnector.setRequestResetCaption(true);
		
		// create composition association between subclass and CompositionClass
		IAssociation compositionModel = IModelElementFactory.instance().createAssociation();
		compositionModel.setFrom(subClass);
		compositionModel.setTo(compositionClass);
		IAssociationEnd compositionFromEnd = (IAssociationEnd) compositionModel.getFromEnd();
		// specify from end as composition as well as the multiplicity
		compositionFromEnd.setAggregationKind(IAssociationEnd.AGGREGATION_KIND_COMPOSITED);
		compositionFromEnd.setMultiplicity("1");
		compositionFromEnd.setName("subclass");
		// specify details for to end.
		IAssociationEnd compositionToEnd = (IAssociationEnd) compositionModel.getToEnd();
		compositionToEnd.setMultiplicity("1..*");
		compositionToEnd.setName("composition");
		// create composition connector on diagram
		IAssociationUIModel compositionConnector = (IAssociationUIModel) diagramManager.createConnector(diagram, compositionModel, subClassShape, compositionClassShape, new Point[] {new Point(366, 348), new Point(366, 422)});
		compositionConnector.setRequestResetCaption(true);
		
		// create association class relationship
		IAssociationClass associationClassModel = IModelElementFactory.instance().createAssociationClass();
		associationClassModel.setFrom(associationModel);
		associationClassModel.setTo(associationClass);
		// create association connector on diagram
		diagramManager.createConnector(diagram, associationClassModel, associationConnector, associationClassShape, null);
		
		// show up the diagram		
		diagramManager.openDiagram(diagram);
	}
	
	public void loadDataType() {
		IProject project = ApplicationManager.instance().getProjectManager().getProject();
		IModelElement[] datatypes = project.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_DATA_TYPE);
		if (datatypes != null && datatypes.length > 0) {
			for (int i = 0; i < datatypes.length; i++) {
				if ("int".equals(datatypes[i].getName())) {
					typeInt = (IDataType) datatypes[i];
				}
				if ("boolean".equals(datatypes[i].getName())) {
					typeBoolean = (IDataType) datatypes[i];
				}
				if ("String".equals(datatypes[i].getName())) {
					typeString = (IDataType) datatypes[i];
				}
				if ("void".equals(datatypes[i].getName())) {
					typeVoid = (IDataType) datatypes[i];
				}
			}
		}		
	}
	
	@Override
	public void update(VPAction arg0) {
		// TODO Auto-generated method stub
		
	}

}

package br.com.loteria.app.infra;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.dozer.event.DozerEvent;
import org.hibernate.proxy.HibernateProxyHelper;

public class DozerEventListener implements org.dozer.DozerEventListener {
	/*private PersistenceUnitUtil persistenceUnitUtil;
	
	public DozerEventListener(PersistenceUnitUtil persistenceUnitUtil){
		this.persistenceUnitUtil = persistenceUnitUtil;
	}*/
	
	@Override
	public void mappingStarted(DozerEvent event) {
		Object dest = event.getDestinationObject();
		if(dest != null)
			clearNotPersistableReferences(dest);
	}

	@Override
	public void preWritingDestinationValue(DozerEvent event) {
		//DO NOTHING
	}

	@Override
	public void postWritingDestinationValue(DozerEvent event) {
		//DO NOTHING
	}

	@Override
	public void mappingFinished(DozerEvent event) {
		Object dest = event.getDestinationObject();
		if(dest != null){
			this.clearNotPersistableNullReferences(dest);
			//this.setBackwardReferences(dest);
		}
	}
	
	private void clearNotPersistableNullReferences(Object obj){
		Class<?> objClass = HibernateProxyHelper.getClassWithoutInitializingProxy(obj);
		if(objClass.getAnnotation(Entity.class)!= null){
			while(!objClass.equals(Object.class)){
				for(Field field : objClass.getDeclaredFields()){
					ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
					OneToOne oneToOne = field.getAnnotation(OneToOne.class);

					ArrayList<CascadeType> cascades = new ArrayList<CascadeType>();
					if(manyToOne != null){
						if(manyToOne.cascade() != null)
							Collections.addAll(cascades, manyToOne.cascade());
					}
					else if(oneToOne != null){
						if(oneToOne.cascade() != null)
							Collections.addAll(cascades, oneToOne.cascade());
					}
					else
						cascades = null;
					
					if(cascades != null){
						try{
							if(!cascades.contains(CascadeType.ALL) && !cascades.contains(CascadeType.PERSIST)){
								Object value = ReflectionHelper.getValue(obj, field);
								if(value != null && ReflectionHelper.isIdNull(value))
									ReflectionHelper.setValue(obj, field, null);
							}
							else{
								Object value = ReflectionHelper.getValue(obj, field);
								if(value != null)
									clearNotPersistableNullReferences(objClass);
							}
						}
						catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e){
							//TODO: Gerar log do erro
						}
					}
				}
				
				objClass = objClass.getSuperclass();
			}
		}
	}
	
	private void clearNotPersistableReferences(Object obj){
		Class<?> objClass = HibernateProxyHelper.getClassWithoutInitializingProxy(obj);
		if(objClass.getAnnotation(Entity.class) !=null){
			while(!objClass.equals(Object.class)){
				for(Field field : objClass.getDeclaredFields()){
					ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
					OneToOne oneToOne = field.getAnnotation(OneToOne.class);
					OneToMany oneToMany = field.getAnnotation(OneToMany.class);

					ArrayList<CascadeType> cascades = new ArrayList<CascadeType>();
					if(manyToOne != null){
						if(manyToOne.cascade() != null)
							Collections.addAll(cascades, manyToOne.cascade());
					}
					else if(oneToOne != null){
						if(oneToOne.cascade() != null)
							Collections.addAll(cascades, oneToOne.cascade());
					}
					else if(oneToMany != null){
						if(oneToMany.cascade() != null)
							Collections.addAll(cascades, oneToMany.cascade());
					}
					else
						cascades = null;
					
					
					if(cascades != null){
						try{
							if(!cascades.contains(CascadeType.ALL) && !cascades.contains(CascadeType.PERSIST)){
								if(oneToMany == null)
									ReflectionHelper.setValue(obj, field, null);
							}
							else{
								Object value = ReflectionHelper.getValue(obj, field);
								if(value != null){
									if(oneToMany == null)
										this.clearNotPersistableReferences(value);
									else{
										for(Object iterator : (Iterable<?>)value)
											this.clearNotPersistableReferences(iterator);
									}
								}
							}
						}
						catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e){
							//TODO: Gerar log do erro
						}
					}
				}
				
				objClass = objClass.getSuperclass();
			}
		}
	}
	
	/*private void setBackwardReferences(Object obj){
		Class<?> objClass = HibernateProxyHelper.getClassWithoutInitializingProxy(obj);
		if(objClass.getAnnotation(Entity.class)!=null && this.persistenceUnitUtil.isLoaded(obj)){
			while(!objClass.equals(Object.class)){
				for(Field field : objClass.getDeclaredFields()){
					try{
						if(this.persistenceUnitUtil.isLoaded(obj, field.getName())){
							if(field.getAnnotation(OneToOne.class) !=null){
								String mappedBy = field.getAnnotation(OneToOne.class).mappedBy();
								if(mappedBy != null && !mappedBy.isEmpty()){
									Object value = ReflectionHelper.getValue(obj, field);
									if(value != null){
										ReflectionHelper.setValue(value, mappedBy, obj);
										setBackwardReferences(value);
									}
								}
							}
							else if(field.getAnnotation(OneToMany.class)!=null){
								String mappedBy = field.getAnnotation(OneToMany.class).mappedBy();
								if(mappedBy != null && !mappedBy.isEmpty()){
									Iterable<?> iterable = (Iterable<?>)ReflectionHelper.getValue(obj, field);
									if(iterable != null){
										for(Object value : iterable){
											if(value != null){
												ReflectionHelper.setValue(value, mappedBy, obj);
												setBackwardReferences(value);
											}
										}
									}
								}
							}
						}
					}
					catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e){
						//TODO: Gerar log do erro
					}
				}
				objClass = objClass.getSuperclass();
			}
		}
	}*/
	
}

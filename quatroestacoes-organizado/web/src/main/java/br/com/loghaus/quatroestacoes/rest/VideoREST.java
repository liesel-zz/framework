package br.com.loghaus.quatroestacoes.rest;

import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.supero.framework.base.rest.BaseREST;
import br.com.supero.framework.base.security.LazyAware;
import br.com.supero.framework.base.security.Security;
import br.com.loghaus.quatroestacoes.entity.Video;
import br.com.loghaus.quatroestacoes.facade.VideoFacade;
import br.com.loghaus.quatroestacoes.filter.VideoFilterData;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.logging.Logger;

@Path("/video")
@RequestScoped
@LazyAware
@Security
public class VideoREST extends BaseREST {

	@Inject
	private Logger log;

	@Inject
	private VideoFacade facade;
	
	@Inject
	private Validator validator;
	
	@GET
	@Path("/findById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Video findById(@PathParam("id") Long id) {
		VideoFilterData filter = new VideoFilterData(id);
        filter.setFetchTag(true);

		Video item = facade.findByFilter(filter);

		if (item == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		return item;
	}

	@GET
    @Path("/findAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Video> list() {
		return facade.findAll();
	}

	@GET
	@Path("/findByFilter")
	@Produces(MediaType.APPLICATION_JSON)
	public DataPage<Video> findByFilter(@QueryParam("nome") String nome,
										  @QueryParam("url") String url,
										  // default page fields
										  @QueryParam("startRow") int startRow,
										  @QueryParam("pageSize") int pageSize) {

		
		VideoFilterData filter = new VideoFilterData(nome, url);
		filter.setOrderBy(VideoFilterData.BY_ID_ASC);
		return facade.findByFilter(filter, new Page((startRow - 1) * pageSize, pageSize));
	}
	
	@GET
	@Path("/zoom")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("Admin")
	public DataPage<Video> zoom(@QueryParam("nome") String nome,
								    // default page fields
								    @QueryParam("startRow") int startRow,
								    @QueryParam("pageSize") int pageSize) {
		
		VideoFilterData filter = new VideoFilterData(nome);

        return facade.zoom(filter, new Page((startRow - 1) * pageSize, pageSize));
	}


	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("Admin")
	public Response save(Video item) {
		Response.ResponseBuilder builder = null;
		try {
            System.out.println(item.toString());
			
			// NOVO RESETA ID
			item.setId(null);
			
			validate(item, CREATE);
			facade.persist(item);

			// Create an "ok" response
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (ValidationException e) {
			// Handle the unique constrain violation
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.CONFLICT).entity(
					responseObj);
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(
					responseObj);
		}
		
		return builder.build();
	}
	
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("Admin")
	public Response update(@PathParam("id") Long id, Video item) {
		Response.ResponseBuilder builder = null;
		try {
			validate(item, UPDATE);
			facade.merge(item);
			// Create an "ok" response
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (ValidationException e) {
			// Handle the unique constrain violation
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.CONFLICT).entity(
					responseObj);
		} catch (Exception e) {

			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(
					responseObj);
		}
		
		return builder.build();
	}

	@DELETE
	@Path("/remove/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("Admin")
	public Response remove(@PathParam("id") Long id) {
		Response.ResponseBuilder builder = null;

		try {
			Video item = facade.findById(id);
			facade.remove(item);
			// Create an "ok" response
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (ValidationException e) {
			// Handle the unique constrain violation
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.CONFLICT).entity(
					responseObj);
		} catch (Exception e) {

			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(
					responseObj);
		}

		return builder.build();
	}
	
	/**
	 * 
	 * */
	private void validate(Video Video, int operationType) throws ConstraintViolationException, ValidationException {
		
		if (operationType == CREATE || operationType == UPDATE) {
			Set<ConstraintViolation<Video>> violations = validator.validate(Video);
	
			if (!violations.isEmpty()) {
				throw new ConstraintViolationException(
						new HashSet<ConstraintViolation<?>>(violations));
			}
		}

	}
	
}

package fr.theogiraudet.dispenser_api.web.rest;

import fr.theogiraudet.dispenser_api.domain.MinecraftAsset;
import fr.theogiraudet.dispenser_api.dto.CustomPage;
import fr.theogiraudet.dispenser_api.dto.reduced_resource.ReducedAsset;
import fr.theogiraudet.dispenser_api.web.rest.abstract_resources.JsonController;
import fr.theogiraudet.dispenser_api.web.rest.dummy.ReducedAssetCustomPage;
import fr.theogiraudet.dispenser_api.web.swagger.CustomPageableAsQueryParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Blockstates files
 */
@RestController
@Tag(name = "Blockstates")
public class BlockStatesController extends JsonController {

    /**
     * Create a new Minecraft Blockstates Controller
     */
    protected BlockStatesController() {
        super(MinecraftAsset.BLOCK_STATES);
    }

    @CustomPageableAsQueryParam
    @Operation(summary = "Get a page of the list of all Blockstates files for a specified version", operationId = "get_all_blockstates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blockstates files found an returned",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReducedAssetCustomPage.class))),
            @ApiResponse(responseCode = "404", description = "Specified version doesn't exist or doesn't have this resource type", content = @Content)
    })
    @GetMapping(path = "/{version}/block/blockstates", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<CustomPage<ReducedAsset>> getAll(@ParameterObject @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 20) Pageable pageable,
                                                           @Parameter(description = "Version from which to get the list")
                                                           @PathVariable String version) {
        return super.getAll(pageable, version);
    }

    @Operation(summary = "Get a specific Blockstates file for a specified version", operationId = "get_block_blockstates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blockstates file found an returned",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Specified version doesn't exist or doesn't have this resource", content = @Content)
    })
    @GetMapping(path = "/{version}/block/blockstates/{assetName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<String> getAsset(
            @Parameter(description = "Blockstates file name to get") @PathVariable String assetName,
            @Parameter(description = "Version from which to get the blockstates") @PathVariable String version) {
        return super.getAsset(assetName, version);
    }
}

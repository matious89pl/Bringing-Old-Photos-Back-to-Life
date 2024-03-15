package rec.file.validation.util.constants;

public class FileValidationConstants {

	public static final String CONF_NAME = 			"conf_name";
	public static final String HAS_BLOCKS = 		"has_blocks";
	public static final String HAS_HEADERS = 		"has_headers";
	public static final String ENABLED_EXTENSIONS = "enabled_extensions";
	public static final String EXTENSION = 			"extension";
	public static final String DELIMITER = 			"delimiter";
	public static final String FILE_TYPE = 			"file_type";
	public static final String COLUMNS = 			"columns";
	public static final String BLOCK_NAME = 		"blockName";
	public static final String NAME = 				"name";
	public static final String START_POSITION = 	"startPosition";
	public static final String MAX_LENGTH = 		"maxLength";
	public static final String MIN_LENGTH = 		"minLength";
	public static final String CONDITIONAL = 		"conditional";
	public static final String COLUMN_NAME = 		"columnName";
	public static final String OPERATION = 			"operation";
	public static final String DATA = 				"data";
	public static final String MANDATORY = 			"mandatory";
	public static final String TYPE = 				"type";
	public static final String FORMAT = 			"format";
	public static final String VALUES = 			"values";
	public static final String MANDATORY_VALIDATION="M";
	public static final String OPTIONAL_VALIDATION ="O";
	public static final String INVALID_VALIDATION = "INVALID";
	public static final String CUSTOM_OPTIONAL =    "CUSTOM_O";

	public static final String REPLACE_FILENAME = 	"{fileName}";
	public static final String REPLACE_REASON = 	"{rejectionReason}";
	public static final String REPLACE_ROW = 		"{row}";
	public static final String REPLACE_COLUMN = 	"{column}";
	
	public static final String ERROR_R001 = 		"{fileName} was rejected. The rejection reason is {rejectionReason}";
	public static final String ERROR_R002 = 		"{fileName} was rejected. The rejection reason is {rejectionReason}";
	public static final String ERROR_R003 = 		"{fileName} was rejected. The rejection reason is {rejectionReason}. This is the first issue found and there may be other issues within the file. Please review and fix any issues and resubmit the file.";
	public static final String ERROR_R003_ZIP = 	"{fileName} within the zip archive was rejected. The rejection reason is {rejectionReason}. There may be other issues within the file or files within the zip. Please review and fix any issues and resubmit the complete file. All files within the zip have been rejected and no files have been stored.";
	public static final String ERROR_R004 = 		"{fileName} was rejected. The rejection reason is {rejectionReason}. This is the first issue found and there may be other issues within the file. Please review and fix any issues and resubmit the file.";
	public static final String ERROR_R004_ZIP = 	"{fileName} within the zip archive was rejected. The rejection reason is {rejectionReason}. There may be other issues within the file or files within the zip. Please review and fix any issues and resubmit the complete file. All files within the zip have been rejected and no files have been stored.";
	public static final String REJECTION_LEVEL_1 = 	"file extension not valid.";
	public static final String REJECTION_LEVEL_2 = 	"file extension does not match file type.";
	public static final String REJECTION_LEVEL_3 = 	"the file structure is incorrect and may contain missing columns or columns that are ordered incorrectly. Validation failed on row {row}, column {column}.";
	public static final String REJECTION_LEVEL_4 = 	"the file content is incorrect and may contain data values that are missing, badly formatted or invalid. Validation failed on row {row}, column {column}.";

		
}

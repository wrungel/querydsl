package com.mysema.query.suites;

import com.mysema.query.*;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.spatial.PostGISTemplates;
import com.mysema.testutil.ExternalDB;

import org.junit.BeforeClass;
import org.junit.experimental.categories.Category;

@Category(ExternalDB.class)
public class PostgreSQLSuiteTest extends AbstractSuite {

    public static class BeanPopulation extends BeanPopulationBase {}
    public static class Delete extends DeleteBase {}
    public static class Insert extends InsertBase {}
    public static class KeywordQuoting extends KeywordQuotingBase {

        private Configuration previous;

        @Override
        public void setUp() throws Exception {
            //NOTE: replacing the templates with a non-quoting one
            previous = configuration;
            configuration = new Configuration(PostGISTemplates.builder().newLineToSingleSpace().build());
            super.setUp();
        }

        @Override
        public void tearDown() throws Exception {
            super.tearDown();
            //NOTE: restoring old templates
            configuration = previous;
        }

    }
    public static class LikeEscape extends LikeEscapeBase {}
    public static class Merge extends MergeBase {}
    public static class Select extends SelectBase {}
    public static class Spatial extends SpatialBase {}
    public static class SelectWindowFunctions extends SelectWindowFunctionsBase {}
    public static class Subqueries extends SubqueriesBase {}
    public static class Types extends TypesBase {}
    public static class Union extends UnionBase {}
    public static class Update extends UpdateBase {}

    // with literals
    public static class DeleteUseLiterals extends DeleteUseLiteralsBase {}
    public static class InsertUseLiterals extends InsertUseLiteralsBase {}
    public static class MergeUseLiterals extends MergeUseLiteralsBase {}
    public static class SelectUseLiterals extends SelectUseLiteralsBase {}
    public static class UpdateUseLiterals extends UpdateUseLiteralsBase {}

    @BeforeClass
    public static void setUp() throws Exception {
        Connections.initPostgres();
        Connections.setTemplates(PostGISTemplates.builder().quote().newLineToSingleSpace().build());
    }

}

// $ANTLR 3.4 /Users/apple/Documents/workspace_research/RJava_Prototype/components/antlr/runtime/JavaScript/tests/functional/t058rewriteAST74.g 2013-01-03 10:57:09

var t058rewriteAST74Parser = function(input, state) {
    if (!state) {
        state = new org.antlr.runtime.RecognizerSharedState();
    }

    (function(){
    }).call(this);

    t058rewriteAST74Parser.superclass.constructor.call(this, input, state);



    /* @todo only create adaptor if output=AST */
    this.adaptor = new org.antlr.runtime.tree.CommonTreeAdaptor();

};

org.antlr.lang.augmentObject(t058rewriteAST74Parser, {
    : ,
    : ,
    : ,
    : ,
    : ,
    : 
});

(function(){
// public class variables
var = ,
    = ,
    = ,
    = ,
    = ,
    = ;

// public instance methods/vars
org.antlr.lang.extend(t058rewriteAST74Parser, org.antlr.runtime.Parser, {
setTreeAdaptor: function(adaptor) {
    this.adaptor = adaptor;
},
getTreeAdaptor: function() {
    return this.adaptor;
},

    getTokenNames: function() { return t058rewriteAST74Parser.tokenNames; },
    getGrammarFileName: function() { return "/Users/apple/Documents/workspace_research/RJava_Prototype/components/antlr/runtime/JavaScript/tests/functional/t058rewriteAST74.g"; }
});
org.antlr.lang.augmentObject(t058rewriteAST74Parser.prototype, {

    // inline static return class
    _return: (function() {
        t058rewriteAST74Parser._return
     = function(){};
        org.antlr.lang.extend(
    t058rewriteAST74Parser._return
    ,
                          org.antlr.runtime.ParserRuleReturnScope,
        {
            getTree: function() { return this.tree; }
        });
        return;
    })(),


    // /Users/apple/Documents/workspace_research/RJava_Prototype/components/antlr/runtime/JavaScript/tests/functional/t058rewriteAST74.g:4:1: a : ( ID )? INT -> ( ID )+ INT ;
    // $ANTLR start "a"
    a: function() {
        var retval = new 
        t058rewriteAST74Parser._return
        ();
        retval.start = this.input.LT(1);


        var root_0 = null;

        var  = null;
        var  = null;

        var _tree=null;
        var _tree=null;
        var stream_=new org.antlr.runtime.tree.RewriteRuleTokenStream(this.adaptor,"token ");
        var stream_=new org.antlr.runtime.tree.RewriteRuleTokenStream(this.adaptor,"token ");

        try {
            // /Users/apple/Documents/workspace_research/RJava_Prototype/components/antlr/runtime/JavaScript/tests/functional/t058rewriteAST74.g:4:3: ( ( ID )? INT -> ( ID )+ INT )
            // /Users/apple/Documents/workspace_research/RJava_Prototype/components/antlr/runtime/JavaScript/tests/functional/t058rewriteAST74.g:4:5: ( ID )? INT




            // AST REWRITE
            // elements: ID, INT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            var stream_=new org.antlr.runtime.tree.RewriteRuleSubtreeStream(this.adaptor,"token ",!=null?.tree:null);

            root_0 = this.adaptor.nil();
            // 4:13: -> ( ID )+ INT
            {
                if ( !(stream_ID.hasNext()) ) {
                    throw new org.antlr.runtime.tree.RewriteEarlyExitException();
                }
                while ( stream_ID.hasNext() ) {
                    this.adaptor.addChild(root_0, 
                    stream_ID.nextNode()
                    );

                }
                stream_ID.reset();

                this.adaptor.addChild(root_0, 
                stream_INT.nextNode()
                );

            }


            retval.tree = root_0;



            retval.stop = this.input.LT(-1);


            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (re) {
            if (re instanceof org.antlr.runtime.RecognitionException) {
                this.reportError(re);
                this.recover(this.input,re);
                retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
            } else {
                throw re;
            }
        }

        finally {
        }
        return 
    retval
    ;
    },


    // inline static return class
    _return: (function() {
        t058rewriteAST74Parser._return
     = function(){};
        org.antlr.lang.extend(
    t058rewriteAST74Parser._return
    ,
                          org.antlr.runtime.ParserRuleReturnScope,
        {
            getTree: function() { return this.tree; }
        });
        return;
    })(),


    // /Users/apple/Documents/workspace_research/RJava_Prototype/components/antlr/runtime/JavaScript/tests/functional/t058rewriteAST74.g:5:1: op : ( '+' | '-' );
    // $ANTLR start "op"
    op: function() {
        var retval = new 
        t058rewriteAST74Parser._return
        ();
        retval.start = this.input.LT(1);


        var root_0 = null;

        var  = null;

        var _tree=null;

        try {
            // /Users/apple/Documents/workspace_research/RJava_Prototype/components/antlr/runtime/JavaScript/tests/functional/t058rewriteAST74.g:5:4: ( '+' | '-' )
            // /Users/apple/Documents/workspace_research/RJava_Prototype/components/antlr/runtime/JavaScript/tests/functional/t058rewriteAST74.g:
            root_0 = this.adaptor.nil();






            retval.stop = this.input.LT(-1);


            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (re) {
            if (re instanceof org.antlr.runtime.RecognitionException) {
                this.reportError(re);
                this.recover(this.input,re);
                retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), re);
            } else {
                throw re;
            }
        }

        finally {
        }
        return 
    retval
    ;
    }

    // Delegated rules




}, true); // important to pass true to overwrite default implementations

 

// public class variables
org.antlr.lang.augmentObject(t058rewriteAST74Parser, {
    tokenNames: ["<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "INT", "WS", "'+'", "'-'"],

});

})();
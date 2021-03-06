﻿using System;
using System.Collections.Generic;
using System.Linq.Expressions;
using System.Web.Mvc;
using System.Web.Routing;
using System.Collections;
using System.Text;

/// <summary>
///  Model-based CheckBoxList extensions
/// </summary>
public static class MvcCheckBoxList_Extensions_Model
{


    /// <summary>
    /// Added by Tudor Saru
    /// </summary>
    /// <typeparam name="TModel"></typeparam>
    /// <typeparam name="TProperty"></typeparam>
    /// <param name="htmlHelper"></param>
    /// <param name="expression"></param>
    /// <param name="selectList"></param>
    /// <returns></returns>



    public static MvcHtmlString CheckBoxListFor<TModel, TProperty>(this HtmlHelper<TModel> htmlHelper, Expression<Func<TModel, TProperty>> expression, IEnumerable<SelectListItem> selectList)
    {

        return CheckBoxListFor<TModel, TProperty>(htmlHelper, expression, selectList, null, 1);

    }

    public static MvcHtmlString CheckBoxListFor<TModel, TProperty>(this HtmlHelper<TModel> htmlHelper, Expression<Func<TModel, TProperty>> expression, IEnumerable<SelectListItem> selectList, int numberOfColumns)
    {

        return CheckBoxListFor<TModel, TProperty>(htmlHelper, expression, selectList, null, numberOfColumns);

    }

    public static MvcHtmlString CheckBoxListFor<TModel, TProperty>(this HtmlHelper<TModel> htmlHelper, Expression<Func<TModel, TProperty>> expression, IEnumerable<SelectListItem> selectList,

      object htmlAttributes, int numberOfColumns)
    {

        return CheckBoxListFor<TModel, TProperty>(htmlHelper, expression, selectList, ((IDictionary<string, object>)new RouteValueDictionary(htmlAttributes)), numberOfColumns);

    }

    public static MvcHtmlString CheckBoxListFor<TModel, TProperty>(this HtmlHelper<TModel> htmlHelper, Expression<Func<TModel, TProperty>> expression, IEnumerable<SelectListItem> selectList,

      IDictionary<string, object> htmlAttributes, int numberOfColumns)
    {

        string name = ExpressionHelper.GetExpressionText((LambdaExpression)expression);

        name = htmlHelper.ViewContext.ViewData.TemplateInfo.GetFullHtmlFieldName(name);

        // Get the property (and assume IEnumerable)

        IEnumerable currentValues = (IEnumerable)expression.Compile().Invoke(htmlHelper.ViewData.Model);

        int columnCount = 0;

        StringBuilder sb = new StringBuilder();

        sb.Append("<ul id=\"list\">");

        foreach (var option in selectList)
        {

            columnCount++;

            TagBuilder builder = new TagBuilder("input");

            if (null != currentValues)
            {

                var enumerator = currentValues.GetEnumerator();

                while (enumerator.MoveNext())
                {

                    if (enumerator.Current.ToString() == option.Value)
                    {

                        builder.MergeAttribute("checked", "checked");
                        break;

                    }

                }

            }

            builder.MergeAttributes<string, object>(htmlAttributes);

            builder.MergeAttribute("type", "checkbox");

            builder.MergeAttribute("value", option.Value);

            builder.MergeAttribute("name", name);

            builder.InnerHtml = option.Text;

            sb.Append("<li>");

            sb.Append(builder.ToString(TagRenderMode.Normal));

            if (columnCount == numberOfColumns)
            {

                columnCount = 0;


            }
            sb.Append("</li>");

        }
        sb.Append("</ul>");
        return MvcHtmlString.Create(sb.ToString());

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /// <summary>
    /// Model-Based function (For...)
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <typeparam name="TProperty">ViewModel property</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listNameExpr">ViewModel Item type to serve as a name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    /// 


    public static MvcHtmlString CheckBoxListFor<TModel, TProperty, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         Expression<Func<TModel, TProperty>> listNameExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        ModelMetadata modelMetadata = ModelMetadata.FromLambdaExpression(listNameExpr, htmlHelper.ViewData);
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, modelMetadata, listNameExpr.toProperty(), sourceDataExpr, valueExpr,
             textToDisplayExpr, htmlAttributesExpr, selectedValuesExpr, null, null, null);
    }

    /// <summary>
    /// Model-Based function
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listName">Name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxList<TModel, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         string listName,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, null, listName, sourceDataExpr, valueExpr, textToDisplayExpr, htmlAttributesExpr,
             selectedValuesExpr, null, null, null);
    }

    /// <summary>
    /// Model-Based function (For...)
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <typeparam name="TProperty">ViewModel property</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listNameExpr">ViewModel Item type to serve as a name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="position">Direction of the list (e.g. 'Position2.Horizontal' or 'Position2.Vertical')</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxListFor<TModel, TProperty, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         Expression<Func<TModel, TProperty>> listNameExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         Position position,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        ModelMetadata modelMetadata = ModelMetadata.FromLambdaExpression(listNameExpr, htmlHelper.ViewData);
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, modelMetadata, listNameExpr.toProperty(), sourceDataExpr, valueExpr,
             textToDisplayExpr, htmlAttributesExpr, selectedValuesExpr, null, null, null, position);
    }

    /// <summary>
    /// Model-Based function
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listName">Name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="position">Direction of the list (e.g. 'Position2.Horizontal' or 'Position2.Vertical')</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxList<TModel, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         string listName,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         Position position,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, null, listName, sourceDataExpr, valueExpr, textToDisplayExpr, htmlAttributesExpr,
             selectedValuesExpr, null, null, null, position);
    }

    /// <summary>
    /// Model-Based function (For...)
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <typeparam name="TProperty">ViewModel property</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listNameExpr">ViewModel Item type to serve as a name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="htmlAttributes">Each checkbox HTML tag attributes (e.g. 'new { class="somename" }')</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxListFor<TModel, TProperty, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         Expression<Func<TModel, TProperty>> listNameExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         object htmlAttributes,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        ModelMetadata modelMetadata = ModelMetadata.FromLambdaExpression(listNameExpr, htmlHelper.ViewData);
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, modelMetadata, listNameExpr.toProperty(), sourceDataExpr, valueExpr,
             textToDisplayExpr, htmlAttributesExpr, selectedValuesExpr, htmlAttributes, null, null);
    }

    /// <summary>
    /// Model-Based function (For...)
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <typeparam name="TProperty">ViewModel property</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listNameExpr">ViewModel Item type to serve as a name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="imgToDisplayExpr"> </param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="htmlAttributes">Each checkbox HTML tag attributes (e.g. 'new { class="somename" }')</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxListFor<TModel, TProperty, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         Expression<Func<TModel, TProperty>> listNameExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
        Expression<Func<TItem, TKey>> imgToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         object htmlAttributes,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        ModelMetadata modelMetadata = ModelMetadata.FromLambdaExpression(listNameExpr, htmlHelper.ViewData);
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, modelMetadata, listNameExpr.toProperty(), sourceDataExpr, valueExpr,
             textToDisplayExpr, imgToDisplayExpr, htmlAttributesExpr, selectedValuesExpr, htmlAttributes, null, null);
    }

    /// <summary>
    /// Model-Based function
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listName">Name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="htmlAttributes">Each checkbox HTML tag attributes (e.g. 'new { class="somename" }')</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxList<TModel, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         string listName,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         object htmlAttributes,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, null, listName, sourceDataExpr, valueExpr, textToDisplayExpr, htmlAttributesExpr,
             selectedValuesExpr, htmlAttributes, null, null);
    }

    /// <summary>
    /// Model-Based function (For...)
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <typeparam name="TProperty">ViewModel property</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listNameExpr">ViewModel Item type to serve as a name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="htmlAttributes">Each checkbox HTML tag attributes (e.g. 'new { class="somename" }')</param>
    /// <param name="position">Direction of the list (e.g. 'Position2.Horizontal' or 'Position2.Vertical')</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes, to allow override of htmlAttributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxListFor<TModel, TProperty, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         Expression<Func<TModel, TProperty>> listNameExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         object htmlAttributes,
         Position position,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        ModelMetadata modelMetadata = ModelMetadata.FromLambdaExpression(listNameExpr, htmlHelper.ViewData);
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, modelMetadata, listNameExpr.toProperty(), sourceDataExpr, valueExpr, textToDisplayExpr,
             htmlAttributesExpr, selectedValuesExpr, htmlAttributes, null, null, position);
    }

    /// <summary>
    /// Model-Based function
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listName">Name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="htmlAttributes">Each checkbox HTML tag attributes (e.g. 'new { class="somename" }')</param>
    /// <param name="position">Direction of the list (e.g. 'Position2.Horizontal' or 'Position2.Vertical')</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes, to allow override of htmlAttributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxList<TModel, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         string listName,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         object htmlAttributes,
         Position position,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, null, listName, sourceDataExpr, valueExpr, textToDisplayExpr, htmlAttributesExpr,
             selectedValuesExpr, htmlAttributes, null, null, position);
    }

    /// <summary>
    /// Model-Based function (For...)
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <typeparam name="TProperty">ViewModel property</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listNameExpr">ViewModel Item type to serve as a name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="htmlAttributes">Each checkbox HTML tag attributes (e.g. 'new { class="somename" }')</param>
    /// <param name="disabledValues">String array of values to disable</param>
    /// <param name="position">Direction of the list (e.g. 'Position2.Horizontal' or 'Position2.Vertical')</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes, to allow override of htmlAttributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxListFor<TModel, TProperty, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         Expression<Func<TModel, TProperty>> listNameExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         object htmlAttributes,
         string[] disabledValues,
         Position position,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        ModelMetadata modelMetadata = ModelMetadata.FromLambdaExpression(listNameExpr, htmlHelper.ViewData);
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, modelMetadata, listNameExpr.toProperty(), sourceDataExpr, valueExpr, textToDisplayExpr,
             htmlAttributesExpr, selectedValuesExpr, htmlAttributes, null, disabledValues, position);
    }

    /// <summary>
    /// Model-Based function
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listName">Name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="htmlAttributes">Each checkbox HTML tag attributes (e.g. 'new { class="somename" }')</param>
    /// <param name="disabledValues">String array of values to disable</param>
    /// <param name="position">Direction of the list (e.g. 'Position2.Horizontal' or 'Position2.Vertical')</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes, to allow override of htmlAttributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxList<TModel, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         string listName,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         object htmlAttributes,
         string[] disabledValues,
         Position position,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, null, listName, sourceDataExpr, valueExpr, textToDisplayExpr, htmlAttributesExpr,
             selectedValuesExpr, htmlAttributes, null, disabledValues, position);
    }

    /// <summary>
    /// Model-Based function (For...)
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <typeparam name="TProperty">ViewModel property</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listNameExpr">ViewModel Item type to serve as a name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="wrapInfo">Settings for HTML wrapper of the list (e.g. 'new HtmlListInfo2(HtmlTag2.vertical_columns, 2, new { style="color:green;" })')</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxListFor<TModel, TProperty, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         Expression<Func<TModel, TProperty>> listNameExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         HtmlListInfo wrapInfo,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        ModelMetadata modelMetadata = ModelMetadata.FromLambdaExpression(listNameExpr, htmlHelper.ViewData);
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, modelMetadata, listNameExpr.toProperty(), sourceDataExpr, valueExpr, textToDisplayExpr,
             htmlAttributesExpr, selectedValuesExpr, null, wrapInfo, null);
    }

    /// <summary>
    /// Model-Based function
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listName">Name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="wrapInfo">Settings for HTML wrapper of the list (e.g. 'new HtmlListInfo2(HtmlTag2.vertical_columns, 2, new { style="color:green;" })')</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxList<TModel, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         string listName,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         HtmlListInfo wrapInfo,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, null, listName, sourceDataExpr, valueExpr, textToDisplayExpr, htmlAttributesExpr,
             selectedValuesExpr, null, wrapInfo, null);
    }

    /// <summary>
    /// Model-Based function (For...)
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <typeparam name="TProperty">ViewModel property</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listNameExpr">ViewModel Item type to serve as a name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="wrapInfo">Settings for HTML wrapper of the list (e.g. 'new HtmlListInfo2(HtmlTag2.vertical_columns, 2, new { style="color:green;" })')</param>
    /// <param name="disabledValues">String array of values to disable</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxListFor<TModel, TProperty, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         Expression<Func<TModel, TProperty>> listNameExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         HtmlListInfo wrapInfo,
         string[] disabledValues,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        ModelMetadata modelMetadata = ModelMetadata.FromLambdaExpression(listNameExpr, htmlHelper.ViewData);
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, modelMetadata, listNameExpr.toProperty(), sourceDataExpr, valueExpr, textToDisplayExpr,
             htmlAttributesExpr, selectedValuesExpr, null, wrapInfo, disabledValues);
    }

    /// <summary>
    /// Model-Based function
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listName">Name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="wrapInfo">Settings for HTML wrapper of the list (e.g. 'new HtmlListInfo2(HtmlTag2.vertical_columns, 2, new { style="color:green;" })')</param>
    /// <param name="disabledValues">String array of values to disable</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxList<TModel, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         string listName,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         HtmlListInfo wrapInfo,
         string[] disabledValues,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, null, listName, sourceDataExpr, valueExpr, textToDisplayExpr, htmlAttributesExpr,
             selectedValuesExpr, null, wrapInfo, disabledValues);
    }

    /// <summary>
    /// Model-Based function (For...)
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <typeparam name="TProperty">ViewModel property</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listNameExpr">ViewModel Item type to serve as a name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="htmlAttributes">Each checkbox HTML tag attributes (e.g. 'new { class="somename" }')</param>
    /// <param name="wrapInfo">Settings for HTML wrapper of the list (e.g. 'new HtmlListInfo2(HtmlTag2.vertical_columns, 2, new { style="color:green;" })')</param>
    /// <param name="disabledValues">String array of values to disable</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes, to allow override of htmlAttributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxListFor<TModel, TProperty, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         Expression<Func<TModel, TProperty>> listNameExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         object htmlAttributes,
         HtmlListInfo wrapInfo,
         string[] disabledValues,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        ModelMetadata modelMetadata = ModelMetadata.FromLambdaExpression(listNameExpr, htmlHelper.ViewData);
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, modelMetadata, listNameExpr.toProperty(), sourceDataExpr, valueExpr, textToDisplayExpr,
             htmlAttributesExpr, selectedValuesExpr, htmlAttributes, wrapInfo, disabledValues);
    }

    /// <summary>
    /// Model-Based function
    /// </summary>
    /// <typeparam name="TModel">Current ViewModel</typeparam>
    /// <typeparam name="TItem">ViewModel Item</typeparam>
    /// <typeparam name="TValue">ViewModel Item type of the value</typeparam>
    /// <typeparam name="TKey">ViewModel Item type of the key</typeparam>
    /// <param name="htmlHelper">MVC Html helper class that is being extended</param>
    /// <param name="listName">Name of each checkbox in a list (use this name to POST list values array back to the controller)</param>
    /// <param name="sourceDataExpr">Data list to be used as a source for the list (set in viewmodel)</param>
    /// <param name="valueExpr">Data list value type to be used as checkbox 'Value'</param>
    /// <param name="textToDisplayExpr">Data list value type to be used as checkbox 'Text'</param>
    /// <param name="selectedValuesExpr">Data list of selected items (should be of same data type as a source list)</param>
    /// <param name="htmlAttributes">Each checkbox HTML tag attributes (e.g. 'new { class="somename" }')</param>
    /// <param name="wrapInfo">Settings for HTML wrapper of the list (e.g. 'new HtmlListInfo2(HtmlTag2.vertical_columns, 2, new { style="color:green;" })')</param>
    /// <param name="disabledValues">String array of values to disable</param>
    /// <param name="htmlAttributesExpr">Data list HTML tag attributes, to allow override of htmlAttributes for each checkbox</param>
    /// <returns>HTML string containing checkbox list</returns>
    public static MvcHtmlString CheckBoxList<TModel, TItem, TValue, TKey>
        (this HtmlHelper<TModel> htmlHelper,
         string listName,
         Expression<Func<TModel, IEnumerable<TItem>>> sourceDataExpr,
         Expression<Func<TItem, TValue>> valueExpr,
         Expression<Func<TItem, TKey>> textToDisplayExpr,
         Expression<Func<TModel, IEnumerable<TItem>>> selectedValuesExpr,
         object htmlAttributes,
         HtmlListInfo wrapInfo,
         string[] disabledValues,
         Expression<Func<TItem, dynamic>> htmlAttributesExpr = null)
    {
        return MvcCheckBoxList.CheckBoxList_ModelBased
            (htmlHelper, null, listName, sourceDataExpr, valueExpr, textToDisplayExpr, htmlAttributesExpr,
             selectedValuesExpr, htmlAttributes, wrapInfo, disabledValues);
    }
}
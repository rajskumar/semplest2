//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Collections.Specialized;

namespace SemplestModel
{
    public partial class PromotionAdengineStatu
    {
        #region Primitive Properties
    
        public virtual int PromotionAdengineStatusPK
        {
            get;
            set;
        }
    
        public virtual int PromotionFK
        {
            get { return _promotionFK; }
            set
            {
                if (_promotionFK != value)
                {
                    if (Promotion != null && Promotion.PromotionPK != value)
                    {
                        Promotion = null;
                    }
                    _promotionFK = value;
                }
            }
        }
        private int _promotionFK;
    
        public virtual int PromotionStatusFK
        {
            get;
            set;
        }
    
        public virtual int AdvertisingEngineFK
        {
            get { return _advertisingEngineFK; }
            set
            {
                if (_advertisingEngineFK != value)
                {
                    if (AdvertisingEngine != null && AdvertisingEngine.AdvertisingEnginePK != value)
                    {
                        AdvertisingEngine = null;
                    }
                    _advertisingEngineFK = value;
                }
            }
        }
        private int _advertisingEngineFK;

        #endregion
        #region Navigation Properties
    
        public virtual AdvertisingEngine AdvertisingEngine
        {
            get { return _advertisingEngine; }
            set
            {
                if (!ReferenceEquals(_advertisingEngine, value))
                {
                    var previousValue = _advertisingEngine;
                    _advertisingEngine = value;
                    FixupAdvertisingEngine(previousValue);
                }
            }
        }
        private AdvertisingEngine _advertisingEngine;
    
        public virtual Promotion Promotion
        {
            get { return _promotion; }
            set
            {
                if (!ReferenceEquals(_promotion, value))
                {
                    var previousValue = _promotion;
                    _promotion = value;
                    FixupPromotion(previousValue);
                }
            }
        }
        private Promotion _promotion;

        #endregion
        #region Association Fixup
    
        private void FixupAdvertisingEngine(AdvertisingEngine previousValue)
        {
            if (previousValue != null && previousValue.PromotionAdengineStatus.Contains(this))
            {
                previousValue.PromotionAdengineStatus.Remove(this);
            }
    
            if (AdvertisingEngine != null)
            {
                if (!AdvertisingEngine.PromotionAdengineStatus.Contains(this))
                {
                    AdvertisingEngine.PromotionAdengineStatus.Add(this);
                }
                if (AdvertisingEngineFK != AdvertisingEngine.AdvertisingEnginePK)
                {
                    AdvertisingEngineFK = AdvertisingEngine.AdvertisingEnginePK;
                }
            }
        }
    
        private void FixupPromotion(Promotion previousValue)
        {
            if (previousValue != null && previousValue.PromotionAdengineStatus.Contains(this))
            {
                previousValue.PromotionAdengineStatus.Remove(this);
            }
    
            if (Promotion != null)
            {
                if (!Promotion.PromotionAdengineStatus.Contains(this))
                {
                    Promotion.PromotionAdengineStatus.Add(this);
                }
                if (PromotionFK != Promotion.PromotionPK)
                {
                    PromotionFK = Promotion.PromotionPK;
                }
            }
        }

        #endregion
    }
}
